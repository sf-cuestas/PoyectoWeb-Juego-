package com.proyectoJuegoCalabozos.Proyecto.Controller;

import java.util.List;

import com.proyectoJuegoCalabozos.Proyecto.Model.Exit;
import com.proyectoJuegoCalabozos.Proyecto.Model.Room;
import com.proyectoJuegoCalabozos.Proyecto.Repository.ExitRepository;
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
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ExitRepository exitRepository;

    @GetMapping("/all")
    public String allRoom(Model model) {
        List<Room> rooms = roomRepository.findAll();
        model.addAttribute("rooms", rooms);
        return "Room-templates/room-list";
    }

    @GetMapping("/create")
    public String createRoom(Model model) {
        Room room = new Room("",  "");
        model.addAttribute("room", room);
        return "Room-templates/room-create";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute Room room, Model model) {
        System.out.println(room.getId());
        roomRepository.save(room);
        return "redirect:/rooms/all";
    }

    @GetMapping("/edit/{id}")
    public String editRoom(Model model, @PathVariable Long id) {
        Room room = roomRepository.findById(id).get();
        if(room != null)
        {
            model.addAttribute("room", room);
            return "Room-templates/room-edit";

        }
        return "Room-templates/room-edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteRoom(Model model, @PathVariable Long id) {
        for(Exit salida : roomRepository.findById(id).get().getExits()){
            exitRepository.delete(salida); 
        }
        roomRepository.findById(id).get().disconnection();
        System.out.println(id);
        roomRepository.save(roomRepository.findById(id).get());

        roomRepository.deleteById(id);
        return "redirect:/rooms/all";
    }
}
