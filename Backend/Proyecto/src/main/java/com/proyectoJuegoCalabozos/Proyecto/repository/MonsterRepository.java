package com.proyectoJuegoCalabozos.Proyecto.repository;
import com.proyectoJuegoCalabozos.Proyecto.model.Monster;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonsterRepository extends JpaRepository<Monster,Long>{
   
    
}
