package com.proyectoJuegoCalabozos.Proyecto.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Monsters {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String name;
    String last_updated;
    int attack_level;
    int defence_slash;
    int size;
    int hitpoints;
    String category;
    String examine;
    String wiki_url;

    
    
    public Monsters() {
    }

    public Monsters(String name, String last_updated, int attack_level, int defence_slash, int size, int hitpoints,
            String category, String examine, String wiki_url) {
        this.name = name;
        this.last_updated = last_updated;
        this.attack_level = attack_level;
        this.defence_slash = defence_slash;
        this.size = size;
        this.hitpoints = hitpoints;
        this.category = category;
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
    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }
    public int getAttack_level() {
        return attack_level;
    }
    public void setAttack_level(int attack_level) {
        this.attack_level = attack_level;
    }
    public int getDefence_slash() {
        return defence_slash;
    }
    public void setDefence_slash(int defence_slash) {
        this.defence_slash = defence_slash;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public int getHitpoints() {
        return hitpoints;
    }
    public void setHitpoints(int hitpoints) {
        this.hitpoints = hitpoints;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
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
