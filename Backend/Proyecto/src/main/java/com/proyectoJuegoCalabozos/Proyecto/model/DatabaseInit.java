package com.proyectoJuegoCalabozos.Proyecto.model;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.proyectoJuegoCalabozos.Proyecto.repository.DecorativesRepository;
import com.proyectoJuegoCalabozos.Proyecto.repository.ExitRepository;
import com.proyectoJuegoCalabozos.Proyecto.repository.ItemRepository;
import com.proyectoJuegoCalabozos.Proyecto.repository.MonsterRepository;
import com.proyectoJuegoCalabozos.Proyecto.repository.MonstersEspRepository;
import com.proyectoJuegoCalabozos.Proyecto.repository.PlayerRepository;
import com.proyectoJuegoCalabozos.Proyecto.repository.RoomRepository;

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

    @Autowired
    PlayerRepository playerRepository;


    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        //Generates items in database from json file
        generateItems();
        //Generates decoratives in database from json file
        generateDecoratives();
        //Generates monster types in database from json file
        generateMonsterTypes();
       

        
        
        /*
        // Players
        playerRepository.save(new Player("escobartc","123",100,10,10,1,0));
        playerRepository.save(new Player("chamy","123",100,10,10,1,0));
        playerRepository.save(new Player("javier","1234",100,10,10,1,0));



        //Tipos de monstruo
        monsterEspRepository.save(new MonstersEsp("Molanisk","2021-09-02",40,45,1,52,"molanisks","A strange mole-like being.","https://oldschool.runescape.wiki/w/wMolanisk"));
        monsterEspRepository.save(new MonstersEsp("Aberrant spectre","2021-09-02",1,20,2,90,"aberrant spectres","A very smelly ghost.","https://oldschool.runescape.wiki/w/Aberrant_spectre"));
        monsterEspRepository.save(new MonstersEsp("Nechryael","2021-09-24",97,20,1,105,"nechryael","An evil death demon.","https://oldschool.runescape.wiki/w/Nechryael#Normal"));
        monsterEspRepository.save(new MonstersEsp("Death spawn","2021-08-05",67,20,1,60,"","An evil death spawn.","https://oldschool.runescape.wiki/w/Death_spawn"));
        monsterEspRepository.save(new MonstersEsp("Zombie","2021-09-02",8,0,1,22,"zombies","Dead man walking.","https://oldschool.runescape.wiki/w/Zombie#Level_13"));

        //Decorativos
        decorativesRepository.save(new Decoratives("Crate"));
        decorativesRepository.save(new Decoratives("Cave Entrance"));
        decorativesRepository.save(new Decoratives("Door"));
        decorativesRepository.save(new Decoratives("Broken multicannon"));
        decorativesRepository.save(new Decoratives("Dwarf multicannon"));

        //Items
        itemRepository.save(new Items("Dwarf remains","2021-09-24",1,16,"The body of a Dwarf savaged by Goblins.","https://oldschool.runescape.wiki/w/Dwarf_remains"));
        itemRepository.save(new Items("Toolkit","2021-08-05",1,0.453,"Good for repairing broken cannons.","https://oldschool.runescape.wiki/w/Toolkit"));
        itemRepository.save(new Items("Cannonball","2021-08-05",5,0,"Ammo for the Dwarf Cannon.","https://oldschool.runescape.wiki/w/Cannonball"));
        itemRepository.save(new Items("Nulodion's notes","2021-08-05",1,0.028,"Construction notes for Dwarf cannon ammo.","https://oldschool.runescape.wiki/w/Nulodion's_notes"));
        itemRepository.save(new Items("Ammo mould","2021-08-05",5,4.535,"Used to make cannon ammunition.","https://oldschool.runescape.wiki/w/Ammo_mould"));

        //Monstruos
        monsterRepository.save(new Monster("Paco",1000));
        monsterRepository.save(new Monster("Luis",1000));
        monsterRepository.save(new Monster("Pepe",1000));
        monsterRepository.save(new Monster("Vladimir",1000));
        monsterRepository.save(new Monster("X AE A-XII",1000));

        //Habitaciones
        roomRepository.save(new Room("Red Room", "Room with a lot of blood"));
        roomRepository.save(new Room("Blue Room", "Room with water "));
        roomRepository.save(new Room("Green Room", "Plants room"));
        roomRepository.save(new Room("Pink Room", "Flowers room"));
        roomRepository.save(new Room("Yellow Room", "Cheese room"));
        

        //Salidas
        exitRepository.save(new Exit());
        exitRepository.save(new Exit());
        exitRepository.save(new Exit());
        exitRepository.save(new Exit());
       

        // For para poner tipos a los monstruos
        for(int i=0; i < monsterRepository.findAll().size(); i++){
         monsterRepository.findAll().get(i).setMonsterEsp(monsterEspRepository.findAll().get(i));
         monsterRepository.save(monsterRepository.findAll().get(i));
         monsterEspRepository.findAll().get(i).getMonstruos().add(monsterRepository.findAll().get(i));
        }

        // For para poner items en los cuartos
        for(Room room : roomRepository.findAll()){
            for(Items item : itemRepository.findAll()){
                room.getItems().add(item);
                roomRepository.save(room);
            }
        }

        // For para poner decorativos a los cuartos

        for(Room room : roomRepository.findAll()){
            for(Decoratives decorative : decorativesRepository.findAll()){
                room.getDecoratives().add(decorative);
                roomRepository.save(room);
            }
        }

        // For para poner monstros en habitaciones
        for(int i=0; i < roomRepository.findAll().size(); i++){
            monsterRepository.findAll().get(i).setRoom(roomRepository.findAll().get(i));
            roomRepository.findAll().get(i).setMonster(monsterRepository.findAll().get(i));
            monsterRepository.save(monsterRepository.findAll().get(i));
            roomRepository.save(roomRepository.findAll().get(i));
           }
       


        // For para poner conexiones entre habitaciones y salidas    
        exitRepository.findAll().get(0).setBefore(roomRepository.findAll().get(4));
        exitRepository.findAll().get(0).setAfter(roomRepository.findAll().get(0));
        roomRepository.findAll().get(0).getExits().add(exitRepository.findAll().get(0));

        exitRepository.findAll().get(1).setBefore(roomRepository.findAll().get(0));
        exitRepository.findAll().get(1).setAfter(roomRepository.findAll().get(1));
        roomRepository.findAll().get(1).getExits().add(exitRepository.findAll().get(1));


        exitRepository.findAll().get(2).setBefore(roomRepository.findAll().get(1));
        exitRepository.findAll().get(2).setAfter(roomRepository.findAll().get(2));
        roomRepository.findAll().get(2).getExits().add(exitRepository.findAll().get(2));


        exitRepository.findAll().get(3).setBefore(roomRepository.findAll().get(2));
        exitRepository.findAll().get(3).setAfter(roomRepository.findAll().get(3));
        roomRepository.findAll().get(3).getExits().add(exitRepository.findAll().get(3));
           */
    
    

        
       
    }

    public void generateItems(){
        File input = new File(System.getProperty("user.dir") + File.separator + "assets" + File.separator + "items.json");
            
        
        try{
                String s = Files.readString(input.toPath());
                JsonParser fileElement = new JsonParser();
                JsonArray data = fileElement.parse(s).getAsJsonArray();
                System.out.println("--------------------------------------------------------------------");
                for(JsonElement objeto : data){
                JsonObject jobject = objeto.getAsJsonObject(); 
                Items item = new Items();



                String name = "";
                if(!jobject.get("name").isJsonNull())
                name = jobject.get("name").getAsString();


                String last_updated = "";
                if(!jobject.get("last_updated").isJsonNull())
                last_updated = jobject.get("last_updated").getAsString();

                Integer cost = 0;
                if(!jobject.get("cost").isJsonNull())
                cost = jobject.get("cost").getAsInt();


                double weight = 0;
                if(!jobject.get("weight").isJsonNull())
                weight = jobject.get("weight").getAsDouble();

                String examine = "";
                if(!jobject.get("examine").isJsonNull())
                examine = jobject.get("examine").getAsString();

                String wiki_url = "";
                if(!jobject.get("wiki_url").isJsonNull())
                wiki_url = jobject.get("wiki_url").getAsString();
              

                item.setName(name);
                item.setLast_updated(last_updated);
                item.setCost(cost);
                item.setWeight(weight);
                item.setExamine(examine);
                item.setWiki_url(wiki_url); 
                 
                itemRepository.save(item);
                }

            } catch (FileNotFoundException e) {
                System.out.println("Error input file not found");
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Error processing input file!");
                e.printStackTrace();
            }
        
    }

    public void generateDecoratives(){
        File input = new File(System.getProperty("user.dir") + File.separator + "assets" + File.separator + "objetos-decorativos.json");
            
        
        try{
                String s = Files.readString(input.toPath());
                JsonParser fileElement = new JsonParser();
                JsonArray data = fileElement.parse(s).getAsJsonArray();

                for(JsonElement objeto : data){
                JsonObject jobject = objeto.getAsJsonObject(); 
                Decoratives decorative = new Decoratives ();
                String name = "";     

                if(!jobject.get("name").isJsonNull())
                name = jobject.get("name").getAsString();
                decorative.setName(name);
                decorativesRepository.save(decorative);
                }

            } catch (FileNotFoundException e) {
                System.out.println("Error input file not found");
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Error processing input file!");
                e.printStackTrace();
            }
        
    }

    public void generateMonsterTypes(){
        File input = new File(System.getProperty("user.dir") + File.separator + "assets" + File.separator + "monstruos.json");
            
        
        try{
                String s = Files.readString(input.toPath());
                JsonParser fileElement = new JsonParser();
                JsonArray data = fileElement.parse(s).getAsJsonArray();

                for(JsonElement objeto : data){
                JsonObject jobject = objeto.getAsJsonObject(); 
                MonstersEsp type = new MonstersEsp ();
                String name = "";     
                String last_updated = "";
                Integer attack_level = 0;
                Integer defence_slash = 0;
                Integer size = 0;
                Integer hitpoints = 0;
                String category = "";
                String examine = "";
                String wiki_url = "";

                if(!jobject.get("name").isJsonNull())
                name = jobject.get("name").getAsString();
                type.setName(name);

                if(!jobject.get("last_updated").isJsonNull())
                last_updated = jobject.get("last_updated").getAsString();
                type.setLast_updated(last_updated);

                if(!jobject.get("attack_level").isJsonNull())
                attack_level = jobject.get("attack_level").getAsInt();
                type.setAttack_level(attack_level);

                if(!jobject.get("defence_slash").isJsonNull())
                defence_slash = jobject.get("defence_slash").getAsInt();
                type.setDefence_slash(defence_slash);

                if(!jobject.get("size").isJsonNull())
                size = jobject.get("size").getAsInt();
                type.setSize(size);

                if(!jobject.get("hitpoints").isJsonNull())
                hitpoints = jobject.get("hitpoints").getAsInt();
                type.setHitpoints(hitpoints);

                if(!jobject.get("category").isJsonNull() && jobject.get("category").getAsJsonArray().size()>0)
                {
                    category = jobject.get("category").getAsJsonArray().get(0).getAsString();
                    type.setCategory(category);
                } 
                

                if(!jobject.get("examine").isJsonNull())
                examine = jobject.get("examine").getAsString();
                type.setExamine(examine);

                if(!jobject.get("wiki_url").isJsonNull())
                wiki_url = jobject.get("wiki_url").getAsString();
                type.setWiki_url(wiki_url);


                monsterEspRepository.save(type);
                }

            } catch (FileNotFoundException e) {
                System.out.println("Error input file not found");
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Error processing input file!");
                e.printStackTrace();
            }
        
    }
    
}
