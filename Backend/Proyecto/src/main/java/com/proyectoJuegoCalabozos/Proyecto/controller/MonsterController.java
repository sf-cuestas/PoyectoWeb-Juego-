package com.proyectoJuegoCalabozos.Proyecto.controller;


import java.util.List;

import com.proyectoJuegoCalabozos.Proyecto.model.Monster;
import com.proyectoJuegoCalabozos.Proyecto.model.MonstersEsp;
import com.proyectoJuegoCalabozos.Proyecto.model.Room;
import com.proyectoJuegoCalabozos.Proyecto.repository.MonsterRepository;
import com.proyectoJuegoCalabozos.Proyecto.repository.MonstersEspRepository;
import com.proyectoJuegoCalabozos.Proyecto.repository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/monster")
public class MonsterController {

    @Autowired
    private MonsterRepository monsterRepository;

    @Autowired
    private MonstersEspRepository monstersRepository;

    @Autowired
    private RoomRepository roomRepository;


    @GetMapping("/all")
    public String allMonsters(Model model) {
        List<Monster> monsters = monsterRepository.findAll();
        model.addAttribute("monsters", monsters);
        return "Monster-templates/Monster-list";
    }
    
    @GetMapping("/create")
    public String createMonster(Model model) {
        Monster monster = new Monster("",0);
        List<MonstersEsp> types = monstersRepository.findAll();
        model.addAttribute("monster", monster);
        model.addAttribute("types", types);
        return "Monster-templates/Monster-create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Monster monster, Model model) {

    
            monsterRepository.save(monster);
        
        return "redirect:/monster/all";
    }

    @GetMapping("/edit/{id}")
    public String editMonster(Model model, @PathVariable Long id) {
        List<MonstersEsp> types = monstersRepository.findAll();
        Monster monster = monsterRepository.findById(id).get();
        for(Room r : roomRepository.findAll()){
            if(r.getMonster()!=null&&r.getMonster().getId()==id)
            monster.setRoom(r);
            monsterRepository.save(monster);
        }

        model.addAttribute("types", types);
        
        if(monster != null)
        {
            model.addAttribute("monster", monster);
            return "Monster-templates/Monster-edit";
        }
        return "Monster-templates/Monster-edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteMonster(Model model, @PathVariable Long id) {
        Monster m = monsterRepository.findById(id).get();
        
        if(m.getMonsterEsp()==null||m.getRoom()==null)
        monsterRepository.deleteById(id);
        else {
            m.unlink(m.getRoom(), m.getMonsterEsp());
            monsterRepository.deleteById(id);
        }
        
        return "redirect:/monster/all";
    }


}
