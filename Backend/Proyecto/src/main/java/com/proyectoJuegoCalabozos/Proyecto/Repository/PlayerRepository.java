package com.proyectoJuegoCalabozos.Proyecto.Repository;




import com.proyectoJuegoCalabozos.Proyecto.Model.Player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Long>{
   

    
}
