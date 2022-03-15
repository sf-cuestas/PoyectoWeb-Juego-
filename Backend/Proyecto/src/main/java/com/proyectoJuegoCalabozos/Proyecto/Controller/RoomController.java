package com.proyectoJuegoCalabozos.Proyecto.Controller;

import java.util.ArrayList;
import java.util.List;

import com.proyectoJuegoCalabozos.Proyecto.Model.Exit;
import com.proyectoJuegoCalabozos.Proyecto.Model.Items;
import com.proyectoJuegoCalabozos.Proyecto.Model.Monster;
import com.proyectoJuegoCalabozos.Proyecto.Model.Room;
import com.proyectoJuegoCalabozos.Proyecto.Repository.ExitRepository;
import com.proyectoJuegoCalabozos.Proyecto.Repository.ItemRepository;
import com.proyectoJuegoCalabozos.Proyecto.Repository.MonsterRepository;
import com.proyectoJuegoCalabozos.Proyecto.Repository.RoomRepository;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private MonsterRepository monsterRepository;

    @Autowired
    private ExitRepository exitRepository;

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/all")
    public String allRoom(Model model) {
        List<Room> rooms = roomRepository.findAll();
        model.addAttribute("rooms", rooms);
        return "Room-templates/room-list";
    }

    @GetMapping("/create")
    public String createRoom(Model model) {
        Room room = new Room("",  "");
    
        List<Monster> monsters = new ArrayList<>();
        for(Monster m :monsterRepository.findAll()){
            if(m.getRoom()==null)
            monsters.add(m);
        }

        model.addAttribute("room", room);
        model.addAttribute("monsters", monsters);
        return "Room-templates/room-create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Room room, Model model) {

        if(room.getMonster()!=null){
            room.getMonster().setRoom(room);
            roomRepository.save(room);
            monsterRepository.save(room.getMonster());
        }  else {

            roomRepository.save(room);

        }
                return "redirect:/rooms/all";
    }

    @GetMapping("/edit/{id}")
    public String editRoom(Model model, @PathVariable Long id) {
        Room room = roomRepository.findById(id).get();
        List<Monster> monsters = new ArrayList<>();


        for(Monster m :monsterRepository.findAll()){
            if(m.getRoom()==null||m.getRoom().getId()==id)
            monsters.add(m);
        }

        if(room != null && room.getMonster()!=null)
        {
            monsterRepository.findById(room.getMonster().getId()).get().setRoom(null);
            monsterRepository.save(monsterRepository.findById(room.getMonster().getId()).get());
            room.setMonster(null);
            model.addAttribute("room", room);
            model.addAttribute("monsters", monsters);
        } else {
            model.addAttribute("monsters", monsters);
            model.addAttribute("room", room);
        }

        return "Room-templates/room-edit";

    }

    @GetMapping("/delete/{id}")
    public String deleteRoom(Model model, @PathVariable Long id) {
        for(Room room : roomRepository.findAll()){
            if(room != null)
            for(Exit e : room.getExits()){
                if(e != null){
                    if(e.getAfter()!= null && e.getAfter().getId()==id){
                        e.setAfter(null);
                        e.setBefore(null);
                    }
                    
                    if(e.getBefore()!= null && e.getBefore().getId()==id){
                        e.setBefore(null);
                        e.setAfter(null);
                    }
                }
                
            }
        }
       
        for(Exit salida : roomRepository.findById(id).get().getExits()){
            exitRepository.delete(salida);
        }
        roomRepository.findById(id).get().disconnection();
        roomRepository.save(roomRepository.findById(id).get());
        roomRepository.deleteById(id);
        return "redirect:/rooms/all";
    }


    @GetMapping("/edit/{idRoom}/additems")
    public String addItemsToRoom(Model model, @PathVariable Long idRoom){
        List<Items> items = itemRepository.findAll();
        model.addAttribute("roomItems", items);
        return "Room-templates/room-item-add";
    }

    @GetMapping("/edit/{idRoom}/addItems/{idItem}")
    public String saveItemToRoom(Model model, @PathVariable Long idRoom,@PathVariable Long idItem){
        Room room = roomRepository.getById(idRoom);
        Items item = itemRepository.getById(idItem);
        room.getItems().add(item);
        item.getRooms().add(room);
        roomRepository.save(room);
        itemRepository.save(item);
        return "redirect:/rooms/all";
    }
}
