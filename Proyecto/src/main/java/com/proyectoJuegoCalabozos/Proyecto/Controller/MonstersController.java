package com.proyectoJuegoCalabozos.Proyecto.Controller;

import java.util.List;

import com.proyectoJuegoCalabozos.Proyecto.Model.Monsters;
import com.proyectoJuegoCalabozos.Proyecto.Repository.MonstersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


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
    
    
}
