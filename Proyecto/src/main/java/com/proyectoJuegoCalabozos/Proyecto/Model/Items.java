package com.proyectoJuegoCalabozos.Proyecto.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String name;
    String last_updated;
    Integer cost;
    double weight;
    String examine;
    String wiki_url;
   
    public Items() {
    }

    

    public Items(String name, String last_update, Integer cost, double weight, String examine, String wiki_url) {
        this.name = name;
        this.last_updated = last_update;
        this.cost = cost;
        this.weight = weight;
        this.examine = examine;
        this.wiki_url = wiki_url;
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
    public String getLast_updated() {
        return last_updated;
    }
    public void setLast_updated(String last_update) {
        this.last_updated = last_update;
    }
    public Integer getCost() {
        return cost;
    }
    public void setCost(Integer cost) {
        this.cost = cost;
    }
    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public String getExamine() {
        return examine;
    }
    public void setExamine(String examine) {
        this.examine = examine;
    }
    public String getWiki_url() {
        return wiki_url;
    }
    public void setWiki_url(String wiki_url) {
        this.wiki_url = wiki_url;
    }
}