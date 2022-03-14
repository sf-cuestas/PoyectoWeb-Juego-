package com.proyectoJuegoCalabozos.Proyecto.Controller;

import java.util.List;

import com.proyectoJuegoCalabozos.Proyecto.Model.Player;
import com.proyectoJuegoCalabozos.Proyecto.Repository.PlayerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    
}
