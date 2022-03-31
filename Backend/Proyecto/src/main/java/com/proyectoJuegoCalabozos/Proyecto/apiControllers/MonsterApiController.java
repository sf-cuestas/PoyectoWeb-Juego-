package com.proyectoJuegoCalabozos.Proyecto.apiControllers;

import com.proyectoJuegoCalabozos.Proyecto.model.Monster;
import com.proyectoJuegoCalabozos.Proyecto.repository.MonsterRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/monster")
public class MonsterApiController {

    private Logger log = LoggerFactory.getLogger(getClass());


    @Autowired
    private MonsterRepository monsterRepository;

    // OpenAPI
     @Operation(summary = "Retrieves a monster by id")
     @ApiResponses(value = {
             @ApiResponse(responseCode = "200", description = "Found the monster", content = {
                     @Content(mediaType = "application/json", schema = @Schema(implementation = Monster.class)) }),
    //         // content = @Content --> means that it returns empty
             @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content), 
             @ApiResponse(responseCode = "404", description = "Monster not found", content = @Content) })
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}")
    public Monster findMonster(@PathVariable Long id) {
        return monsterRepository.findById(id).orElseThrow();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("")
    public Monster saveMonster(@RequestBody Monster monster) {
        log.info("monster {}", monster.getName());
        return monsterRepository.save(monster);
    }
}
