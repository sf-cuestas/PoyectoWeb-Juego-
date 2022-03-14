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
@RequestMapping("/monster")
public class MonsterController {

    @Autowired
    private MonsterRepository monsterRepository;

    @Autowired
    private MonstersEspRepository monstersRepository;

    @GetMapping("/all")
    public String allMonsters(Model model) {
        List<Monster> monsters = monsterRepository.findAll();
        model.addAttribute("monsters", monsters);
        return "Monster-templates/Monster-list";
    }
    
    @GetMapping("/create")
    public String createMonster(Model model) {
        Monster monster = new Monster("",0);
        List<MonstersEsp> monsters = monstersRepository.findAll();
        model.addAttribute("monsters", monsters);
        model.addAttribute("monster", monster);
        return "Monster-templates/Monster-create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Monster monster, Model model) {
        System.out.println(monster.getId());
        monsterRepository.save(monster);
        return "redirect:/monster/all";
    }

    @GetMapping("/edit/{id}")
    public String editMonster(Model model, @PathVariable Long id) {
        Monster monster = monsterRepository.findById(id).get();
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
