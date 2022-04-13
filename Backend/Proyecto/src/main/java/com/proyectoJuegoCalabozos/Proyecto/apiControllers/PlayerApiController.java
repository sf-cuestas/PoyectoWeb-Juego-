package com.proyectoJuegoCalabozos.Proyecto.apiControllers;

import java.util.List;

import com.proyectoJuegoCalabozos.Proyecto.model.Items;
import com.proyectoJuegoCalabozos.Proyecto.model.Monster;
import com.proyectoJuegoCalabozos.Proyecto.model.Player;
import com.proyectoJuegoCalabozos.Proyecto.model.Room;
import com.proyectoJuegoCalabozos.Proyecto.repository.ItemRepository;
import com.proyectoJuegoCalabozos.Proyecto.repository.MonsterRepository;
import com.proyectoJuegoCalabozos.Proyecto.repository.PlayerRepository;
import com.proyectoJuegoCalabozos.Proyecto.repository.RoomRepository;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.core.joran.action.ActionUtil;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/player")
public class PlayerApiController {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private ItemRepository itemsRepository;

    @Autowired
    private RoomRepository roomsRepository;

    @Autowired
    private MonsterRepository monsterRepository;

    // OpenAPI
    @Operation(summary = "Retrieves a User by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the User", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Player.class)) }),
   //         // content = @Content --> means that it returns empty
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content), 
            @ApiResponse(responseCode = "404", description = "Monster not found", content = @Content) })
   @CrossOrigin(origins = "http://localhost:4200")
   @GetMapping("/{userName}")
    public Player findPlayerByUserName(@PathVariable String userName) {
        return playerRepository.findPlayerByUsername(userName);
    }

    @CrossOrigin(origins = "http://localhost:4200")
   @GetMapping("/{userId}/{itemId}")
    public Player throwItemByUserName(@PathVariable Long userId, @PathVariable Long itemId) {
        Player actual = playerRepository.findById(userId).get();
        Items item = itemsRepository.findById(itemId).get();
        actual.getBackpack().remove(item);
        item.setPlayer(null);
        actual.getRoom().getItems().add(item);
        roomsRepository.save(actual.getRoom());
        playerRepository.save(actual);
        return actual;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/pickup/{userId}/{itemId}")
    public Player pickUpItemByUserName(@PathVariable Long userId, @PathVariable Long itemId) {
        Player actual = playerRepository.findById(userId).get();
        Items item = itemsRepository.findById(itemId).get();
        actual.getBackpack().add(item);
        item.setPlayer(actual);
        actual.getRoom().getItems().remove(item);
        roomsRepository.save(actual.getRoom());
        playerRepository.save(actual);
        return actual;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/spawn/{userId}")
    public Player spawnPlayer(@PathVariable Long userId) {
        Player actual = playerRepository.findById(userId).get();
        List<Room> rooms = roomsRepository.findAll();
        long random = (long)Math.floor(Math.random()*(rooms.get(rooms.size()-1).getId()-rooms.get(0).getId()+1)+rooms.get(0).getId());
        actual.setRoom(roomsRepository.getById(random));
        playerRepository.save(actual);
        return actual;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/attackmonster/{userId}/{attack}")
    public Player attackMonsterByPlayer(@PathVariable Long userId, @PathVariable int attack) {
        Player actual = playerRepository.findById(userId).get();
        Monster actualMonster = actual.getRoom().getMonster();
        actualMonster.setHp(attack);
        monsterRepository.save(actualMonster);
        playerRepository.save(actual);
        return actual;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/changeroom/{userId}/{roomId}")
    public Player changeRoom(@PathVariable Long userId, @PathVariable long roomId) {
        Player actual = playerRepository.findById(userId).get();
        Room actualRoom = actual.getRoom();
        Room nextRoom = roomsRepository.findById(roomId).get();
        actualRoom.getPlayers().remove(actual);
        actual.setRoom(nextRoom);
        roomsRepository.save(actualRoom);
        roomsRepository.save(nextRoom);
        playerRepository.save(actual);
        return actual;
    }
    
}
