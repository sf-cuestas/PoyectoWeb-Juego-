package com.proyectoJuegoCalabozos.Proyecto.Db;

import java.util.Collection;
import java.util.Map;

public class FakeDb {
    public Map <Integer, Monsters> dataMonsters;

    public FakeDb()
    {
        dataMonsters.put(1, new Monsters(1,"Molanisk","2021-09-02", 40,45, 1,52, "molanisks","A strange mole-like being.","https://oldschool.runescape.wiki/w/Molanisk"));
        dataMonsters.put(2, new Monsters(2,"Aberrant spectre","2021-09-02", 1,20, 2,90, "aberrant spectres","A very smelly ghost.","https://oldschool.runescape.wiki/w/Aberrant_spectre"));
        
    }

    Monsters findMonsters(int key)
    {
        return dataMonsters.get(key);
    }

    Collection<Monsters> findAllMonster()
    {
        return dataMonsters.values();
    }
}
