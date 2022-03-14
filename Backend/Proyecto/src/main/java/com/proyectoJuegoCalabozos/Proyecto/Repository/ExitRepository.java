package com.proyectoJuegoCalabozos.Proyecto.Repository;



import com.proyectoJuegoCalabozos.Proyecto.Model.Exit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExitRepository extends JpaRepository<Exit,Long>{
   

    
}
