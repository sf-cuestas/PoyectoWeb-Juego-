
package com.proyectoJuegoCalabozos.Proyecto.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @OneToMany
    List <Items> itemsRoom;
    @OneToMany
    List <Decoratives> decorativesRoom;
    @OneToOne
    MonstersEsp monster;
    @OneToMany
    List <Exit> exits;


    
    public Room() {
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public List<Items> getItemsRoom() {
        return itemsRoom;
    }
    public void setItemsRoom(List<Items> itemsRoom) {
        this.itemsRoom = itemsRoom;
    }
    public List<Decoratives> getDecorativesRoom() {
        return decorativesRoom;
    }
    public void setDecorativesRoom(List<Decoratives> decorativesRoom) {
        this.decorativesRoom = decorativesRoom;
    }
    public MonstersEsp getMonster() {
        return monster;
    }
    public void setMonster(MonstersEsp monster) {
        this.monster = monster;
    }
    public List<Exit> getExits() {
        return exits;
    }
    public void setExits(List<Exit> exits) {
        this.exits = exits;
    }
    

    

    

    


}
