
package com.proyectoJuegoCalabozos.Proyecto.Repository;

import java.util.List;

import com.proyectoJuegoCalabozos.Proyecto.Model.Decoratives;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DecorativesRepository extends JpaRepository<Decoratives,Long>{
    List<Decoratives> findByName(String name);

}
