package com.proyectoJuegoCalabozos.Proyecto.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Monster {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    @ManyToOne
    MonstersEsp monsterEsp;
    Integer hp;
    

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

    



    
}
