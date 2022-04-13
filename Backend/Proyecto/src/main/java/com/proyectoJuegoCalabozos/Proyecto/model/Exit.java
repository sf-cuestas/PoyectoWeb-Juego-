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
public class Exit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    Room before;

    @OneToOne
    Room after;
    
    public Exit() {
    }

    

    public Exit(Room before, Room after) {
        this.before = before;
        this.after = after;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonBackReference
    public Room getBefore() {
        return before;
    }

    public void setBefore(Room before) {
        this.before = before;
    }

    @JsonIgnore
    public Room getAfter() {
        return after;
    }

    public void setAfter(Room after) {
        this.after = after;
    }

   public void remove(){
    setAfter(null);
    setBefore(null);
   }
   
    
    
}
