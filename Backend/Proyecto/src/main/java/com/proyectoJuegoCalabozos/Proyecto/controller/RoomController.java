package com.proyectoJuegoCalabozos.Proyecto.controller;

import java.util.ArrayList;
import java.util.List;

import com.proyectoJuegoCalabozos.Proyecto.model.Decoratives;
import com.proyectoJuegoCalabozos.Proyecto.model.Exit;
import com.proyectoJuegoCalabozos.Proyecto.model.Items;
import com.proyectoJuegoCalabozos.Proyecto.model.Monster;
import com.proyectoJuegoCalabozos.Proyecto.model.Player;
import com.proyectoJuegoCalabozos.Proyecto.model.Room;
import com.proyectoJuegoCalabozos.Proyecto.repository.DecorativesRepository;
import com.proyectoJuegoCalabozos.Proyecto.repository.ExitRepository;
import com.proyectoJuegoCalabozos.Proyecto.repository.ItemRepository;
import com.proyectoJuegoCalabozos.Proyecto.repository.MonsterRepository;
import com.proyectoJuegoCalabozos.Proyecto.repository.PlayerRepository;
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

    @Autowired
    private DecorativesRepository decorativeRepository;

    @Autowired
    private PlayerRepository playerRepository;

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
            Monster m = room.getMonster();
            m.setRoom(room);
            roomRepository.save(room);
            monsterRepository.save(m);
        }  else {

            roomRepository.save(room);

        }
                return "redirect:/rooms/all";
    }

    @GetMapping("/edit/{id}")
    public String editRoom(Model model, @PathVariable Long id) {
        Room room = roomRepository.findById(id).get();
        List<Monster> monsters = new ArrayList<>();

        //Monstruo actual
        if(room.getMonster()!=null)
        monsters.add(room.getMonster());
        //Monstruos disponibles
        for(Monster m :monsterRepository.findAll()){
            if(m.getRoom()==null)
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
        Room actual = roomRepository.findById(id).get();
        List<Room> rooms = roomRepository.findAll();

        for(Room room : rooms){
            if(room != null)
            for(Exit e : room.getExits()){
                if(e != null){
                    if(e.getAfter()!= null && e.getAfter().getId()==id){
                        e.setAfter(null);
                        e.setBefore(null);
                        exitRepository.save(e);
                    }
                    
                    if(e.getBefore()!= null && e.getBefore().getId()==id){
                        e.setBefore(null);
                        e.setAfter(null);
                        exitRepository.save(e);
                    }
                }
                
            }
        }
       
        for(Exit salida : actual.getExits()){
            exitRepository.delete(salida);
        }

        for(Player p : actual.getPlayers()){
            p.setRoom(null);
            playerRepository.save(p);
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
        model.addAttribute("room", idRoom);
        return "Room-templates/room-item-add";
    }

    @GetMapping("/edit/{idRoom}/additems/{idItem}")
    public String saveItemToRoom(Model model, @PathVariable Long idRoom,@PathVariable Long idItem){
        Room room = roomRepository.getById(idRoom);
        Items item = itemRepository.getById(idItem);
        room.getItems().add(item);
        item.getRooms().add(room);
        roomRepository.save(room);
        itemRepository.save(item);
        return "redirect:/rooms/all";
    }

    @GetMapping("/edit/{idRoom}/removeitems")
    public String removeItemsToRoom(Model model, @PathVariable Long idRoom){
        Room room=roomRepository.getById(idRoom);
        List<Items> items = room.getItems();
        model.addAttribute("roomItems", items);
        model.addAttribute("room", idRoom);

        return "Room-templates/room-item-remove";
    }

    @GetMapping("/edit/{idRoom}/removeitems/{idItem}")
    public String saveRemoveItemToRoom(Model model, @PathVariable Long idRoom,@PathVariable Long idItem){
        Room room = roomRepository.getById(idRoom);
        Items item = itemRepository.getById(idItem);
        room.getItems().remove(item);
        item.getRooms().remove(room);
        roomRepository.save(room);
        itemRepository.save(item);
        return "redirect:/rooms/all";
    }

    @GetMapping("/edit/{idRoom}/adddecorative")
    public String addDecorativeToRoom(Model model, @PathVariable Long idRoom){
        List <Decoratives> decoratives = decorativeRepository.findAll();
        model.addAttribute("roomDecoratives", decoratives);
        model.addAttribute("room", idRoom);
        return "Room-templates/room-decorative-add";
       
    }

    @GetMapping("/edit/{idRoom}/adddecorative/{idDecorative}")
    public String saveDecorativeToRoom(Model model, @PathVariable Long idRoom,@PathVariable Long idDecorative){
        Room room = roomRepository.getById(idRoom);
        Decoratives decorative = decorativeRepository.getById(idDecorative);
        room.getDecoratives().add(decorative);
        decorative.getRoom().add(room);
        roomRepository.save(room);
        decorativeRepository.save(decorative);
        return "redirect:/rooms/all";
    }

    @GetMapping("/edit/{idRoom}/removedecorative")
    public String removeDecorativeToRoom(Model model, @PathVariable Long idRoom){
        Room room=roomRepository.getById(idRoom);
        List<Decoratives> decoratives = room.getDecoratives();
        model.addAttribute("roomDecoratives", decoratives);
        model.addAttribute("room", idRoom);

        return "Room-templates/room-decorative-remove";
    }

    @GetMapping("/edit/{idRoom}/removedecorative/{idDecorative}")
    public String saveRemoveDecorativeToRoom(Model model, @PathVariable Long idRoom,@PathVariable Long idDecorative){
        Room room = roomRepository.getById(idRoom);
        Decoratives decorative = decorativeRepository.getById(idDecorative);
        decorative.removeRoom(room);
        decorativeRepository.save(decorative);
        room.getDecoratives().remove(decorative);
        roomRepository.save(room);
        return "redirect:/rooms/all";
    }
    
    @GetMapping("/edit/{idRoom}/addExits")
    public String addExitsToRoom(Model model, @PathVariable Long idRoom){
        List<Exit> exits = exitRepository.findAll();
        model.addAttribute("roomExits", exits);
        model.addAttribute("room", idRoom);
        return "Room-templates/room-exit-add";
    }

    @GetMapping("/edit/{idRoom}/addExits/{idExit}")
    public String saveExitToRoom(Model model, @PathVariable Long idRoom,@PathVariable Long idExit){
        Room room = roomRepository.getById(idRoom);
        Exit exit = exitRepository.getById(idExit);
        room.getExits().add(exit);
        exit.setBefore(room);
        roomRepository.save(room);
        exitRepository.save(exit);
        return "redirect:/rooms/all";
    }

    @GetMapping("/edit/{idRoom}/removeExits")
    public String removeExitsToRoom(Model model, @PathVariable Long idRoom){
        Room room=roomRepository.getById(idRoom);
        List<Exit> exits = room.getExits();
        model.addAttribute("roomExits", exits);
        model.addAttribute("room", idRoom);

        return "Room-templates/room-exit-remove";
    }

    @GetMapping("/edit/{idRoom}/removeExits/{idExit}")
    public String saveRemoveExitToRoom(Model model, @PathVariable Long idRoom,@PathVariable Long idExit){
        Room room = roomRepository.getById(idRoom);
        Exit exit = exitRepository.getById(idExit);
        room.getExits().remove(exit);
        exit.setBefore(null);
        roomRepository.save(room);
        exitRepository.save(exit);
        return "redirect:/rooms/all";
    }



}

