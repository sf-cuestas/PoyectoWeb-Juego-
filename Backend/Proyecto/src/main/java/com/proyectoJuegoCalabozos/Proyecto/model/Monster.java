package com.proyectoJuegoCalabozos.Proyecto.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Monster {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;

    @ManyToOne
    MonstersEsp monsterEsp;

    Integer hp;

    @OneToOne
    Room room;
  
    

    public Monster() {
    }


    public Monster(String name, Integer hp) {
        this.name = name;
        this.hp = hp;
    }


    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }


    @JsonBackReference
    public MonstersEsp getMonsterEsp() {
        return monsterEsp;
    }



    public void setMonsterEsp(MonstersEsp monsterEsp) {
        this.monsterEsp = monsterEsp;
    }



    public Integer getHp() {
        return hp;
    }



    public void setHp(Integer hp) {
        this.hp = hp;
    }


    public String getName() {
        return name;
    }






    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public Room getRoom() {
        return room;
    }


    public void setRoom(Room room) {
        this.room = room;
    }

    public void unlink(Room r, MonstersEsp t){
        this.setRoom(null);
        this.setMonsterEsp(null);
        r.setMonster(null);
        t.getMonstruos().remove(this);
    }

    
    


    
}
