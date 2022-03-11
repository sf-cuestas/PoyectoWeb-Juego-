package com.proyectoJuegoCalabozos.Proyecto.Model;
import com.proyectoJuegoCalabozos.Proyecto.Repository.ItemRepository;
import com.proyectoJuegoCalabozos.Proyecto.Repository.MonsterRepository;

import java.util.Optional;

import javax.transaction.Transactional;

import com.proyectoJuegoCalabozos.Proyecto.Repository.DecorativesRepository;
import com.proyectoJuegoCalabozos.Proyecto.Repository.ExitRepository;
import com.proyectoJuegoCalabozos.Proyecto.Repository.MonstersEspRepository;
import com.proyectoJuegoCalabozos.Proyecto.Repository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class DatabaseInit implements ApplicationRunner{

    @Autowired
    MonstersEspRepository monsterEspRepository;

    @Autowired
    MonsterRepository monsterRepository;
    
    @Autowired 
    DecorativesRepository decorativesRepository;

    @Autowired
    ItemRepository itemRepository;
    
    @Autowired
    ExitRepository exitRepository;

    @Autowired
    RoomRepository roomRepository;


    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        monsterEspRepository.save(new MonstersEsp("Molanisk","2021-09-02",40,45,1,52,"molanisks","A strange mole-like being.","https://oldschool.runescape.wiki/w/wMolanisk"));
        decorativesRepository.save(new Decoratives("Cuadro"));
        itemRepository.save(new Items("Arma","2021-09-02", 15, 25,"ARMA","wiki.com"));
        monsterRepository.save(new Monster("Paco",1000));
        roomRepository.save(new Room("Red Room", "Room with a lot of blood"));
        roomRepository.save(new Room("Blue Room", "Room with water "));
        roomRepository.save(new Room("Green Room", "Plants room"));
        //exitRepository.save(new Exit());
        
        MonstersEsp monsterEsp = monsterEspRepository.findById(1l).orElseThrow();
        for(Monster monster : monsterRepository.findAll()){
            monster.setMonsterEsp(monsterEsp);
            monsterRepository.save(monster);
        }


        //ExitRepository exits = 






    }
    
}
