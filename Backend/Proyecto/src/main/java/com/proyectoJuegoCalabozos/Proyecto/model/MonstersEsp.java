package com.proyectoJuegoCalabozos.Proyecto.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class MonstersEsp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String name;
    String last_updated;
    Integer attack_level;
    Integer defence_slash;
    Integer size;
    Integer hitpoints;
    String category;
    String examine;
    String wiki_url;

    @OneToMany(mappedBy = "monsterEsp")
    List<Monster> monstruos = new ArrayList<>();
    
    
    public MonstersEsp() {
    }



    public MonstersEsp(String name, String last_updated, Integer attack_level, Integer defence_slash, Integer size, Integer hitpoints,
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
    public Integer getHitpoints() {
        return hitpoints;
    }
    public void setHitpoints(Integer hitpoints) {
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
    @JsonIgnore
    public List<Monster> getMonstruos() {
        return monstruos;
    }

    public void setMonstruos(List<Monster> monstruos) {
        this.monstruos = monstruos;
    }

    public void unlink(Monster m){
        if(getMonstruos()!=null){
            this.monstruos.remove(m);
            m.setMonsterEsp(null);
        }
        
    }

    
}
