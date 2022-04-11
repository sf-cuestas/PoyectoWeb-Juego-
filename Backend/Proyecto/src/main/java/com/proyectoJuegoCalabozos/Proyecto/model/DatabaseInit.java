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
        //Generates rooms with city names from json file
        generateRooms();
        //Generates creatures 
        generateMonsters();
        //Set types to monsters
        putTypesToMonsters();
        //Create and set exits to rooms
        generateExits();
        //Set monsters to rooms
        putMonstersIntoRooms();
        //set items to rooms
        putItemsIntoRooms();
        //set decoratives to rooms
        putDecorativesIntoRooms();

        // Players
        playerRepository.save(new Player("escobartc","123",Role.ADMIN));

        List<Room> roomsxd = roomRepository.findAll();
        List<Items> itemsPlayer = itemRepository.findAll();
        List<Player> activePlayers = playerRepository.findAll();
        for(int i=0;i<1;i++){
         activePlayers.get(0).getBackpack().add(itemsPlayer.get(i));
         itemsPlayer.get(i).setPlayer(activePlayers.get(0));
         itemRepository.save(itemsPlayer.get(i));
        }
        activePlayers.get(0).setRoom(roomsxd.get(0));
        roomsxd.get(0).getPlayers().add(activePlayers.get(0));
        playerRepository.save(activePlayers.get(0));
        roomRepository.save(roomsxd.get(0));


        
        playerRepository.save(new Player("player","123",Role.PLAYER));
        playerRepository.save(new Player("admin","123",Role.ADMIN));
        playerRepository.save(new Player("designer","123",Role.DESIGNER));
        




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

    public void generateRooms(){
        File input = new File(System.getProperty("user.dir") + File.separator + "assets" + File.separator + "cities.json");
            int n = 1100, i=0;

        
        try{
                String s = Files.readString(input.toPath());
                JsonParser fileElement = new JsonParser();
                JsonArray data = fileElement.parse(s).getAsJsonArray();

                for(JsonElement objeto : data){
                JsonObject jobject = objeto.getAsJsonObject(); 
                Room room = new Room ();
                String name = "";     

                if(i >= n)
                return;

                if(!jobject.get("name").isJsonNull())
                name = jobject.get("name").getAsString();
                room.setName(name);
                room.setDescription("This room has a city name which is " + name);
                roomRepository.save(room);
                i++;
                }

            } catch (FileNotFoundException e) {
                System.out.println("Error input file not found");
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Error processing input file!");
                e.printStackTrace();
            }
        
    }

    public void generateMonsters(){
        long n = roomRepository.findAll().size();

    for(int i=0;i<n;i++)
    {
        Monster monster = new Monster("Monster " + getAlphaNumericString(5),1000);
        monsterRepository.save(monster);
    }
    }
    
    // function to generate a random string of length n
    public String getAlphaNumericString(int n)
    {
  
        // chose a Character random from this String
        String AlphaNumericString = "ABC" + "0123456789";
                                    
  
        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);
  
        for (int i = 0; i < n; i++) {
  
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                = (int)(AlphaNumericString.length()
                        * Math.random());
  
            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                          .charAt(index));
        }
  
        return sb.toString();
    }

    public void putTypesToMonsters(){
        List<Monster> monsters = monsterRepository.findAll();
        List<MonstersEsp> types = monsterEspRepository.findAll();
        long min = types.get(0).getId();
        long max = types.get(types.size()-1).getId();

    // For para poner tipos a los monstruos
    for(int i=0; i < monsters.size(); i++){
    long random_long = (long)Math.floor(Math.random()*(max-min+1)+min);
    monsters.get(i).setMonsterEsp(monsterEspRepository.getById(random_long));
    monsterRepository.save(monsters.get(i));
    monsterEspRepository.getById(random_long).getMonstruos().add(monsters.get(i));
    }
    }

    public void generateExits(){
        List<Room> roomsList = roomRepository.findAll();
        long rooms = roomRepository.findAll().size();
        long nsalidas, random, max,min;
        Room room;

    for(int i=0;i<rooms;i++)
    {
        nsalidas = (long)Math.floor(Math.random()*(3-1+1)+1);
        for(int j =0;j<nsalidas;j++)
        {
            if(i<roomsList.size()){
                room = roomsList.get(i);
                max=roomsList.size()-1;
                min=i;
                random = (long)Math.floor(Math.random()*(max-min+1)+min);
                Exit exit = new Exit(roomsList.get(i),roomsList.get((int) random));
                exitRepository.save(exit);
                roomsList.get(i).getExits().add(exit);
                roomRepository.save(roomsList.get(i));
            } else {
                Exit exit = new Exit(roomsList.get(i),roomsList.get(0));
                exitRepository.save(exit);
                roomsList.get(i).getExits().add(exit);
                roomRepository.save(roomsList.get(i));
            }
            
        }
        
    }
    }

    public void putMonstersIntoRooms(){
        List<Room> roomsList = roomRepository.findAll();
        List<Monster> monsterList = monsterRepository.findAll();
        int n = roomsList.size();
        long random;

        for(int i=0;i<n;i++){
            random = (long)Math.floor(Math.random()*(9-0+1)+0);
            if(random%2==0){
                roomsList.get(i).setMonster(monsterList.get(i));
                monsterList.get(i).setRoom(roomsList.get(i));
                monsterRepository.save(monsterList.get(i));
                roomRepository.save(roomsList.get(i));
            }
        }

    }
    public void putDecorativesIntoRooms(){
        List<Room> roomsList = roomRepository.findAll();
        List<Decoratives> decorativesList = decorativesRepository.findAll();
        long rooms = roomRepository.findAll().size();
        long nDecorativos, random, max=decorativesList.get(decorativesList.size()-1).getId(),min=decorativesList.get(0).getId();
        Room room;
        Decoratives deco;
        
        for(int i=0;i<rooms;i++)
    {
        room = roomsList.get(i);
        nDecorativos = (long)Math.floor(Math.random()*(10-1+1)+1);
        for(int j =0;j<nDecorativos;j++)
        {  
                random = (long)Math.floor(Math.random()*(max-min+1)+min);
                deco = decorativesRepository.getById(random);
                room.getDecoratives().add(deco);
                deco.getRoom().add(room);
                decorativesRepository.save(deco);
        }
        roomRepository.save(room);
        
    }



    }
    public void putItemsIntoRooms(){
        List<Room> roomsList = roomRepository.findAll();
        List<Items> itemsList = itemRepository.findAll();
        long rooms = roomRepository.findAll().size();
        long nItems, random, max=itemsList.get(itemsList.size()-1).getId(),min=itemsList.get(0).getId();
        Room room;
        Items item;
        
        for(int i=0;i<rooms;i++)
    {
        room = roomsList.get(i);
        nItems = (long)Math.floor(Math.random()*(12-1+1)+1);
        for(int j =0;j<nItems;j++)
        {  
                random = (long)Math.floor(Math.random()*(max-min+1)+min);
                item = itemRepository.getById(random);
                room.getItems().add(item);
                item.getRooms().add(room);
                itemRepository.save(item);
        }
        roomRepository.save(room);
        
    }
    }

     
}


