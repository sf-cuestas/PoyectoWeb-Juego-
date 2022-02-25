package com.proyectoJuegoCalabozos.Proyecto.Controller;

import java.util.List;
import java.util.Optional;

import com.proyectoJuegoCalabozos.Proyecto.Model.Monsters;
import com.proyectoJuegoCalabozos.Proyecto.Repository.MonstersRepository;

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




@Controller
@RequestMapping("/monsters")
public class MonstersController {

    @Autowired
    private MonstersRepository monstersRepository;

    @GetMapping("/all")
    public String allMonsters(Model model) {
        List<Monsters> monsters = monstersRepository.findAll();
        model.addAttribute("monsters", monsters);
        return "monster-list";
    }
    
    @GetMapping("/create")
    public String createMonster(Model model) {
        Monsters monster = new Monsters("",  "",  0, 0, 0, 0, "", "", "");
        model.addAttribute("monster", monster);

        return "monster-create";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute Monsters monster, Model model) {
        monstersRepository.save(monster);
        return "redirect:/monsters/all";
    }

    @GetMapping("/edit/{id}")
    public String editMonster(Model model, @PathVariable Long id) {
        Optional<Monsters> monster = monstersRepository.findById(id);
        if(monster != null)
        {
            model.addAttribute("monster", monster);
            return "monster-edit";

        }
        

        return "monster-edit";
    }
    
    
    
}
