/*
package com.proyectoJuegoCalabozos.Proyecto.Model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    ArrayList <Items> itemsRoom;
    ArrayList <Decoratives> decorativesRoom;
    Monsters monster;
    ArrayList <Room> rooms;
    ArrayList <Player> players;

    public Room() {
    }

    public Room(ArrayList<Items> itemsRoom, ArrayList<Decoratives> decorativesRoom, Monsters monster,
            ArrayList<Room> rooms, ArrayList<Player> players) {
        this.itemsRoom = itemsRoom;
        this.decorativesRoom = decorativesRoom;
        this.monster = monster;
        this.rooms = rooms;
        this.players = players;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ArrayList<Items> getItemsRoom() {
        return itemsRoom;
    }

    public void setItemsRoom(ArrayList<Items> itemsRoom) {
        this.itemsRoom = itemsRoom;
    }

    public ArrayList<Decoratives> getDecorativesRoom() {
        return decorativesRoom;
    }

    public void setDecorativesRoom(ArrayList<Decoratives> decorativesRoom) {
        this.decorativesRoom = decorativesRoom;
    }

    public Monsters getMonster() {
        return monster;
    }

    public void setMonster(Monsters monster) {
        this.monster = monster;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    

    


}
*/