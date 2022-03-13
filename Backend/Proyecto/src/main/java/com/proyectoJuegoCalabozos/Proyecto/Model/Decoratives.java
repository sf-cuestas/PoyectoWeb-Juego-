package com.proyectoJuegoCalabozos.Proyecto.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Decoratives {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String name;
    @ManyToMany(mappedBy = "decoratives")
    List <Room> room = new ArrayList<>();

    public Decoratives() {
    }

    

    public Decoratives(String name) {
        this.name = name;
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



    public List<Room> getRoom() {
        return room;
    }



    public void setRoom(List<Room> room) {
        this.room = room;
    }
  
    public void removeRoom(Room r){
        this.room.remove(r);
        r.getDecoratives().remove(this);
    }
}