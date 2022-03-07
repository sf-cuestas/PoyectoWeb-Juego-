
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
    @ManyToMany
    List <Items> items = new ArrayList<>();
    @ManyToMany
    List <Decoratives> decoratives = new ArrayList<>();
    @OneToOne
    MonstersEsp monster;
    @OneToMany
    List <Exit> exits = new ArrayList<>();



    
    public Room() {
    }




    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public List<Items> getitems() {
        return items;
    }
    public void setitems(List<Items> items) {
        this.items = items;
    }
    public List<Decoratives> getdecoratives() {
        return decoratives;
    }
    public void setdecoratives(List<Decoratives> decoratives) {
        this.decoratives = decoratives;
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
