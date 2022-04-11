package com.proyectoJuegoCalabozos.Proyecto.controller;

import java.util.List;

import com.proyectoJuegoCalabozos.Proyecto.model.Player;
import com.proyectoJuegoCalabozos.Proyecto.repository.PlayerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    

    @GetMapping("/all")
    public String allPlayers(Model model) {
        List<Player> player = playerRepository.findAll();
        model.addAttribute("player", player);
        return "Player-templates/player-list";
    }

    @GetMapping("/create")
    public String createPlayer(Model model) {
        Player player = new Player();
        model.addAttribute("player", player);

        return "Player-templates/player-create";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute Player player, Model model) {
        System.out.println(player.getId());
        playerRepository.save(player);
        return "redirect:/player/all";
    }

    @GetMapping("/edit/{id}")
    public String editPlayer(Model model, @PathVariable Long id) {
        Player player = playerRepository.findById(id).get();
        if(player != null)
        {
            model.addAttribute("player", player);
            return "Player-templates/player-edit";

        }
        

        return "Player-templates/decorative-edit";
    }

    @GetMapping("/delete/{id}")
    public String deletePlayer (Model model, @PathVariable Long id) {
        playerRepository.deleteById(id);
        return "redirect:/player/all";
    }
    
}
