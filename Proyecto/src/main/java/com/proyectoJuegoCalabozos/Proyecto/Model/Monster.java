package com.proyectoJuegoCalabozos.Proyecto.Model;

import javax.persistence.ManyToOne;

public class Monster {
    @ManyToOne
    MonstersEsp monsterEsp;
    Integer hp;
}
