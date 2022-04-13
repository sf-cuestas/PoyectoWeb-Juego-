package com.proyectoJuegoCalabozos.Proyecto.controller;

import java.util.List;

import com.proyectoJuegoCalabozos.Proyecto.model.Exit;
import com.proyectoJuegoCalabozos.Proyecto.repository.ExitRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exits")
public class ExitController {

    @Autowired
    private ExitRepository exitRepository;

    @GetMapping("/all")
    public String allExits(Model model) {
        List<Exit> exits = exitRepository.findAll();
        model.addAttribute("exits", exits);
        return "Exit-templates/exit-list";
    }

    @GetMapping("/create")
    public String createExit(Model model) {
        Exit exit = new Exit();
        model.addAttribute("exit", exit);

        return "Exit-templates/exit-create";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute Exit exit, Model model) {
        System.out.println(exit.getId());
        exitRepository.save(exit);
        return "redirect:/exits/all";
    }

    @GetMapping("/edit/{id}")
    public String editDecorative(Model model, @PathVariable Long id) {
        Exit exit = exitRepository.findById(id).get();
        if(exit != null)
        {
            model.addAttribute("exit", exit);
            return "Exit-templates/exit-edit";

        }
        

        return "Exit-templates/exit-edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteMonster(Model model, @PathVariable Long id) {
        Exit e = exitRepository.findById(id).get();
        if(e.getAfter()==null && e.getBefore()==null){
            exitRepository.deleteById(id);
        } else {
            e.remove();
            exitRepository.deleteById(id);
        }
        return "redirect:/exits/all";
    }

    
    
}
