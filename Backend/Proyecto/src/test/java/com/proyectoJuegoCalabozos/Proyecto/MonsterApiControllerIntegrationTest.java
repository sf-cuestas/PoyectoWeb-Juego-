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
        monster = new Monster("prueba",100);
        esp = new MonstersEsp("EspPrueba", "prueba", 12, 0, 0, 0, "prueba", "prueba", "prueba");
        monstersEspRepository.save(esp);
        monster.setMonsterEsp(esp);
        ArrayList<Monster> listMonsters = new ArrayList<Monster>();
        listMonsters.add(monster);
        esp.setMonstruos(listMonsters);
        monsterRepository.save(monster);
        monstersEspRepository.save(esp);
    }
    
    @Test
    void findMonsterTest(){
        Monster monsterRest = rest.getForObject("http://localhost:" + port + "/api/monster/" + monster.getId(), Monster.class);
        assertEquals(monster.getId(), monsterRest.getId());
    }

    @Test 
    void getMonsterTypeTest(){
        MonstersEsp monstersEspRest = rest.getForObject("http://localhost:" + port + "/api/monster/type/" + monster.getId(), MonstersEsp.class);
        assertEquals(esp.getId(), monstersEspRest.getId());
    }

    @Test
    void saveMonster(){
        Monster monsterRest = rest.postForObject("http://localhost:" + port + "/api/monster", monster, Monster.class);
        assertEquals(monster.getId(), monsterRest.getId());
    }

}
