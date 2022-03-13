
package com.proyectoJuegoCalabozos.Proyecto.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String username;
    String password;
    Integer hp;
    Integer attack_level;
    Integer defence_slash;
    Integer size;
    Integer weight;
    @OneToMany(mappedBy = "player")
    List <Items> backpack = new ArrayList<>();
    @ManyToOne
    Room room;



    public Player() {
    }






    public Player(String username, String password, Integer hp, Integer attack_level, Integer defence_slash,
            Integer size, Integer weight) {
        this.username = username;
        this.password = password;
        this.hp = hp;
        this.attack_level = attack_level;
        this.defence_slash = defence_slash;
        this.size = size;
        this.weight = weight;
    }






    public long getId() {
        return id;
    }





    public void setId(long id) {
        this.id = id;
    }





    public String getUsername() {
        return username;
    }





    public void setUsername(String username) {
        this.username = username;
    }





    public String getPassword() {
        return password;
    }





    public void setPassword(String password) {
        this.password = password;
    }





    public Integer getHp() {
        return hp;
    }





    public void setHp(Integer hp) {
        this.hp = hp;
    }





    public Integer getAttack_level() {
        return attack_level;
    }





    public void setAttack_level(Integer attack_level) {
        this.attack_level = attack_level;
    }





    public Integer getDefence_slash() {
        return defence_slash;
    }





    public void setDefence_slash(Integer defence_slash) {
        this.defence_slash = defence_slash;
    }





    public Integer getSize() {
        return size;
    }





    public void setSize(Integer size) {
        this.size = size;
    }





    public Integer getWeight() {
        return weight;
    }





    public void setWeight(Integer weight) {
        this.weight = weight;
    }





    public List<Items> getBackpack() {
        return backpack;
    }





    public void setBackpack(List<Items> backpack) {
        this.backpack = backpack;
    }





    public Room getRoom() {
        return room;
    }





    public void setRoom(Room room) {
        this.room = room;
    }
    
    

    
}
