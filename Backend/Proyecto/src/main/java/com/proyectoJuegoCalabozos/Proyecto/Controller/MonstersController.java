package com.proyectoJuegoCalabozos.Proyecto.Controller;

import java.util.List;

import com.proyectoJuegoCalabozos.Proyecto.Model.Monster;
import com.proyectoJuegoCalabozos.Proyecto.Model.MonstersEsp;
import com.proyectoJuegoCalabozos.Proyecto.Repository.MonsterRepository;
import com.proyectoJuegoCalabozos.Proyecto.Repository.MonstersEspRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;





@Controller
@RequestMapping("/monsters")
public class MonstersController {

    @Autowired
    private MonstersEspRepository monstersRepository;

    @Autowired
    private MonsterRepository monsterRepository;

    @GetMapping("/all")
    public String allMonsters(Model model) {
        List<MonstersEsp> monsters = monstersRepository.findAll();
        model.addAttribute("monsters", monsters);
        return "Monsters-templates/monster-list";
    }
    
    @GetMapping("/create")
    public String createMonster(Model model) {
        MonstersEsp monster = new MonstersEsp("",  "",  0, 0, 0, 0, "", "", "");
        model.addAttribute("monster", monster);

        return "Monsters-templates/monster-create";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute MonstersEsp monster, Model model) {
        System.out.println(monster.getId());
        monstersRepository.save(monster);
        return "redirect:/monsters/all";
    }

    @GetMapping("/edit/{id}")
    public String editMonster(Model model, @PathVariable Long id) {
        MonstersEsp monster = monstersRepository.findById(id).get();
        if(monster != null)
        {
            model.addAttribute("monster", monster);
            return "Monsters-templates/monster-edit";

        }
        

        return "Monsters-templates/monster-edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteMonster(Model model, @PathVariable Long id) {

        for(Monster monster : monsterRepository.findAll()){
            if(monster!=null){
                if(monster.getMonsterEsp() != null && monster.getMonsterEsp().getId()==id){
                    monstersRepository.findById(id).get().unlink(monster);
                    }
            } 
            
        } 
        monstersRepository.deleteById(id);
        return "redirect:/monsters/all";
    }
    
    
    
}