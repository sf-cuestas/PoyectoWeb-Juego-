package com.proyectoJuegoCalabozos.Proyecto.repository;



import com.proyectoJuegoCalabozos.Proyecto.model.Exit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExitRepository extends JpaRepository<Exit,Long>{
   

    
}
