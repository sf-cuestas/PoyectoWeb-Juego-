package com.proyectoJuegoCalabozos.Proyecto.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.proyectoJuegoCalabozos.Proyecto.Model.Monsters;

@Repository
public interface MonstersRepository extends JpaRepository<Monsters,Long>{
    List<Monsters> findByName(String name);
}
