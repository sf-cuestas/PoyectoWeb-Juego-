package com.proyectoJuegoCalabozos.Proyecto.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectoJuegoCalabozos.Proyecto.Model.Monster;

@Repository
public interface MonsterRepository extends JpaRepository<Monster,Long>{
   
    
}
