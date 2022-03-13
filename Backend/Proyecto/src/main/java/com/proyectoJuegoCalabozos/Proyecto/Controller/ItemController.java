package com.proyectoJuegoCalabozos.Proyecto.Controller;

import java.util.List;

import com.proyectoJuegoCalabozos.Proyecto.Model.Items;
import com.proyectoJuegoCalabozos.Proyecto.Model.Room;
import com.proyectoJuegoCalabozos.Proyecto.Repository.ItemRepository;
import com.proyectoJuegoCalabozos.Proyecto.Repository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired 
    private RoomRepository roomRepository;

    @GetMapping("/all")
    public String allItems(Model model) {
        List<Items> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "Item-templates/item-list";
    }
    
    @GetMapping("/create")
    public String createItem(Model model) {
        Items item = new Items("", "", 0, 0, "", "");
        model.addAttribute("item", item);

        return "Item-templates/item-create";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute Items item, Model model) {
        System.out.println(item.getId());
        itemRepository.save(item);
        return "redirect:/items/all";
    }

    @GetMapping("/edit/{id}")
    public String ediItem(Model model, @PathVariable Long id) {
        Items item = itemRepository.findById(id).get();
        if(item != null)
        {
            model.addAttribute("item", item);
            return "Item-templates/item-edit";

        }
        

        return "Item-templates/item-edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteItem(Model model, @PathVariable Long id) {


        for(Room room : roomRepository.findAll()){
            itemRepository.findById(id).get().removeRoom(room);
        }


             
        itemRepository.deleteById(id);
        return "redirect:/items/all";
    }
    
}
