package com.proyectoJuegoCalabozos.Proyecto.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import com.proyectoJuegoCalabozos.Proyecto.Model.Decoratives;
import com.proyectoJuegoCalabozos.Proyecto.Repository.DecorativesRepository;



@Controller
@RequestMapping("/decoratives")
public class DecorativesController {
    @Autowired
    private DecorativesRepository decorativesRepository;

    @GetMapping("/all")
    public String allDecoratives(Model model) {
        List<Decoratives> decorative = decorativesRepository.findAll();
        model.addAttribute("decoratives", decorative);
        return "Decoratives-templates/decorative-list";
    }
    
    @GetMapping("/create")
    public String createDecorative(Model model) {
        Decoratives decorative = new Decoratives();
        model.addAttribute("decorative", decorative);

        return "Decoratives-templates/decorative-create";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute Decoratives decorative, Model model) {
        System.out.println(decorative.getId());
        decorativesRepository.save(decorative);
        return "redirect:/decoratives/all";
    }

    @GetMapping("/edit/{id}")
    public String editDecorative(Model model, @PathVariable Long id) {
        Decoratives decorative = decorativesRepository.findById(id).get();
        if(decorative != null)
        {
            model.addAttribute("decorative", decorative);
            return "Decoratives-templates/decorative-edit";

        }
        

        return "Decoratives-templates/decorative-edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteDecorative(Model model, @PathVariable Long id) {
        decorativesRepository.deleteById(id);
        return "redirect:/decoratives/all";
    }
    
}
