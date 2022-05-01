package com.proyectoJuegoCalabozos.Proyecto;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.proyectoJuegoCalabozos.Proyecto.model.Exit;
import com.proyectoJuegoCalabozos.Proyecto.model.Items;
import com.proyectoJuegoCalabozos.Proyecto.model.Monster;
import com.proyectoJuegoCalabozos.Proyecto.model.MonstersEsp;
import com.proyectoJuegoCalabozos.Proyecto.model.Player;
import com.proyectoJuegoCalabozos.Proyecto.model.Role;
import com.proyectoJuegoCalabozos.Proyecto.model.Room;
import com.proyectoJuegoCalabozos.Proyecto.repository.ItemRepository;
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
public class PlayerApiControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate rest;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private ItemRepository itemsRepository;

    @Autowired
    private RoomRepository roomsRepository;

    @Autowired
    private MonsterRepository monsterRepository;

    Player player;
    Items item;
    Room room;
    Monster monster;

    @BeforeEach
    void init(){
        player = new Player("player1","prueba",Role.PLAYER);
        playerRepository.save(player);
        item = new Items("prueba", "PRUEBA", 0, 0, "prueba", "prueba");
        item.setId(1);
        itemsRepository.save(item);
        room = new Room("prueba", "prueba");
        roomsRepository.save(room);
        player.setRoom(room);
        monster = new Monster("prueba", 100);
        monsterRepository.save(monster);
        room.setMonster(monster);
        playerRepository.save(player);
        itemsRepository.save(item);
        roomsRepository.save(room);
        monsterRepository.save(monster);


    }

    @Test
    void findPlayerByUserNameTest(){
        Player playerRest = rest.getForObject("http://localhost:" + port + "/api/player/" + player.getUsername(), Player.class);
        assertEquals(player.getUsername(), playerRest.getUsername());
    }

    @Test
    void throwItemByUserName(){
        player.getBackpack().add(item);
        playerRepository.save(player);
        itemsRepository.save(item);
        Player playerRest = rest.getForObject("http://localhost:" + port + "/api/player/" + player.getId() + "/" + item.getId() , Player.class);
        assertEquals(false, playerRest.getBackpack().contains(item));
    }

    @Test
    void pickUpItemByUserNameTest(){
        Player playerRest = rest.getForObject("http://localhost:" + port + "/api/player/pickup/" + player.getId() + "/" + item.getId() , Player.class);
        assertEquals(item.getId(), playerRest.getBackpack().get(0).getId());
    }

    @Test
    void spawnPlayerTest(){
        Player playerRest = rest.getForObject("http://localhost:" + port + "/api/player/spawn/" + player.getId() , Player.class);
        assertEquals(room.getId(), playerRest.getRoom().getId());
    }

    @Test
    void attackMonsterByPlayerTest(){
        int attack = 50;
        Player playerRest = rest.getForObject("http://localhost:" + port + "/api/player/attackmonster/" + player.getId() + "/" + attack , Player.class);
        monster.setHp(monster.getHp() - attack);
        assertEquals(monster.getHp(), playerRest.getRoom().getMonster().getHp());
    }

    @Test 
    void attackPlayerByMonsterTest(){
        int attack = 50;
        Player playerRest = rest.getForObject("http://localhost:" + port + "/api/player/attackplayer/" + player.getId() + "/" + attack , Player.class);
        player.setHp(player.getHp() - attack);
        assertEquals(player.getHp(), playerRest.getHp());
    }

    @Test
    void changeRoomTest(){
        boolean assertTest = false;
        Room nextRoom = new Room("room 2", "room 2");
        roomsRepository.save(nextRoom);
        Player playerRest = rest.getForObject("http://localhost:" + port + "/api/player/changeroom/" + player.getId() + "/" + nextRoom.getId() , Player.class);
        if(!room.getPlayers().contains(player) && playerRest.getRoom().getId() == nextRoom.getId()){
            assertTest = true;
        }
        assertEquals(true, assertTest);
    }


}
