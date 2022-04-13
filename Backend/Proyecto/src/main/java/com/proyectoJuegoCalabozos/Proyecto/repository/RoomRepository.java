
package com.proyectoJuegoCalabozos.Proyecto.repository;

import java.util.List;

import com.proyectoJuegoCalabozos.Proyecto.model.Room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long>{
    List<Room> findByName(String name);
    


}
