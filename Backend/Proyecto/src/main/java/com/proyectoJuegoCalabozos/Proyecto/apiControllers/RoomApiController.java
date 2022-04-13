package com.proyectoJuegoCalabozos.Proyecto.apiControllers;
import java.util.List;

import com.proyectoJuegoCalabozos.Proyecto.model.Items;
import com.proyectoJuegoCalabozos.Proyecto.model.Player;
import com.proyectoJuegoCalabozos.Proyecto.model.Room;
import com.proyectoJuegoCalabozos.Proyecto.repository.ItemRepository;
import com.proyectoJuegoCalabozos.Proyecto.repository.PlayerRepository;
import com.proyectoJuegoCalabozos.Proyecto.repository.RoomRepository;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/room")
public class RoomApiController {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private ItemRepository itemsRepository;

    @Autowired
    private RoomRepository roomsRepository;

    // OpenAPI
    @Operation(summary = "Retrieves a User by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the User", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Player.class)) }),
   //         // content = @Content --> means that it returns empty
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content), 
            @ApiResponse(responseCode = "404", description = "Monster not found", content = @Content) })
   @CrossOrigin(origins = "http://localhost:4200")
   @GetMapping("/{playerId}")
    public Room findActualPlayerRoom(@PathVariable Long playerId) {

        return roomsRepository.findById(playerRepository.findById(playerId).get().getRoom().getId()).get();
    }
}
