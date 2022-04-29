package com.proyectoJuegoCalabozos.Proyecto;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.proyectoJuegoCalabozos.Proyecto.model.Monster;
import com.proyectoJuegoCalabozos.Proyecto.model.MonstersEsp;
import com.proyectoJuegoCalabozos.Proyecto.model.Player;
import com.proyectoJuegoCalabozos.Proyecto.model.Role;
import com.proyectoJuegoCalabozos.Proyecto.model.Room;
import com.proyectoJuegoCalabozos.Proyecto.repository.MonsterRepository;
import com.proyectoJuegoCalabozos.Proyecto.repository.MonstersEspRepository;
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
public class MonsterApiControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate rest;

    @Autowired
    private MonsterRepository monsterRepository;

    @Autowired
    private MonstersEspRepository monstersEspRepository;

    @Autowired
    RoomRepository roomRepository;

    Monster monster;
    MonstersEsp esp;
    Room room;
    
    @BeforeEach
    void init() {
        monster = new Monster();
        esp = new MonstersEsp("EspPrueba", "prueba", 12, 0, 0, 0, "prueba", "prueba", "prueba");
        monster.setMonsterEsp(esp);
        room = new Room();
        room.setMonster(monster);
        monster.setRoom(room);
        esp.getMonstruos().add(monster);
        monsterRepository.save(monster);
        monstersEspRepository.save(esp);
        roomRepository.save(room);

    }

}
