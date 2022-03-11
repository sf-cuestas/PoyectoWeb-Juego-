
package com.proyectoJuegoCalabozos.Proyecto.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String name;
    String description;
    @ManyToMany
    List <Items> items = new ArrayList<>();
    @ManyToMany
    List <Decoratives> decoratives = new ArrayList<>();
    @OneToOne
    Monster monster;
    @OneToMany(mappedBy = "before")
    List <Exit> exits = new ArrayList<>();


    
    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }



    public Room() {
    }



    public long getId() {
        return id;
    }



    public void setId(long id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }



    public String getDescription() {
        return description;
    }



    public void setDescription(String description) {
        this.description = description;
    }



    public List<Items> getItems() {
        return items;
    }



    public void setItems(List<Items> items) {
        this.items = items;
    }



    public List<Decoratives> getDecoratives() {
        return decoratives;
    }



    public void setDecoratives(List<Decoratives> decoratives) {
        this.decoratives = decoratives;
    }



    public Monster getMonster() {
        return monster;
    }



    public void setMonster(Monster monster) {
        this.monster = monster;
    }



    public List<Exit> getExits() {
        return exits;
    }



    public void setExits(List<Exit> exits) {
        this.exits = exits;
    }
    

    

    

    

    


}
