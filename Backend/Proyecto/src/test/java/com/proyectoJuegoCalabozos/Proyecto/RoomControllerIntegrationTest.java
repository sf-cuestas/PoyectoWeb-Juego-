package com.proyectoJuegoCalabozos.Proyecto;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import com.proyectoJuegoCalabozos.Proyecto.model.Player;
import com.proyectoJuegoCalabozos.Proyecto.model.Role;
import com.proyectoJuegoCalabozos.Proyecto.model.Room;
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
public class RoomControllerIntegrationTest {
    
    @LocalServerPort
    private int port;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    PlayerRepository playerRepository;

    Room r;
    Player p;

    @BeforeEach
    void init() {
        r = new Room("Room 1","Habitación de prueba");
        p = new Player("playerp1","123",Role.PLAYER);
        Player p2 = new Player("playerp2","123",Role.PLAYER);
        Player p3 = new Player("playerp3","123",Role.PLAYER);
        r.getPlayers().add(p);
        r.getPlayers().add(p2);
        r.getPlayers().add(p3);
        p.setRoom(r);
        p2.setRoom(r);
        p3.setRoom(r);
        roomRepository.save(r);
        playerRepository.save(p);
        playerRepository.save(p2);
        playerRepository.save(p3);
    }

    @Autowired
    private TestRestTemplate rest;

    @Test
    void listPlayersFromRoom(){
        Player[] players = rest.getForObject("http://localhost:" + port + "/api/room/roomplayers/1", Player[].class);

        assertEquals(players[0].getId(),r.getPlayers().get(0).getId());
    }

    @Test
    void aaaa(){
        Player[] players = rest.getForObject("http://localhost:" + port + "/api/room/roomplayers/1", Player[].class);

        assertEquals(players.length,r.getPlayers().size());
    }

}
