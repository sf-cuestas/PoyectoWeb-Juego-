package com.proyectoJuegoCalabozos.Proyecto.repository;


import java.util.List;

import com.proyectoJuegoCalabozos.Proyecto.model.Items;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Items,Long>{
    List<Items> findByName(String name);

    
}
