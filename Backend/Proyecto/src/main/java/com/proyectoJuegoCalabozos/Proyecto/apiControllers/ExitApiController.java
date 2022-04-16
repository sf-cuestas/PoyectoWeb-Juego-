package com.proyectoJuegoCalabozos.Proyecto.apiControllers;

import com.proyectoJuegoCalabozos.Proyecto.model.Room;
import com.proyectoJuegoCalabozos.Proyecto.repository.ExitRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/exit")
public class ExitApiController {
    @Autowired
    private ExitRepository exitRepository;
    
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("nextroom/{idExit}")
    public Room nextRoom(@PathVariable Long idExit) {
        return exitRepository.findById(idExit).get().getAfter();
    }
}
