package com.proyectoJuegoCalabozos.Proyecto.Repository;


import java.util.List;

import com.proyectoJuegoCalabozos.Proyecto.Model.Items;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Items,Long>{

    
}
