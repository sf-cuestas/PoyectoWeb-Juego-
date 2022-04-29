package com.proyectoJuegoCalabozos.Proyecto;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.proyectoJuegoCalabozos.Proyecto.model.Exit;
import com.proyectoJuegoCalabozos.Proyecto.model.Player;
import com.proyectoJuegoCalabozos.Proyecto.model.Role;
import com.proyectoJuegoCalabozos.Proyecto.model.Room;
import com.proyectoJuegoCalabozos.Proyecto.repository.ExitRepository;
import com.proyectoJuegoCalabozos.Proyecto.repository.PlayerRepository;
import com.proyectoJuegoCalabozos.Proyecto.repository.RoomRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("integrationtest")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ExitApiControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate rest;

    @Autowired
    private ExitRepository exitRepository;

    @Autowired
    RoomRepository roomRepository;

    Room room1;
    Room room2;
    Exit exit;

    @BeforeEach
    void init() {
        room1 = new Room("Room 1","Habitación de prueba1");
        room2 = new Room("Room 2","Habitación de prueba2");
        exit = new Exit(room1, room2);
        roomRepository.save(room1);
        roomRepository.save(room2);
        exitRepository.save(exit);
    }

    @Test
    void nextRoomTest(){
        Room nextRoom = rest.getForObject("http://localhost:" + port + "/api/exit/nextroom/" + exit.getId(), Room.class);
        assertEquals(exit.getAfter().getId(), nextRoom.getId());
    }
    
}
