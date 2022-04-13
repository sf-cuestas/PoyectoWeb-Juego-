package com.proyectoJuegoCalabozos.Proyecto.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.proyectoJuegoCalabozos.Proyecto.model.MonstersEsp;

@Repository
public interface MonstersEspRepository extends JpaRepository<MonstersEsp,Long>{
    List<MonstersEsp> findByName(String name);
    
}
