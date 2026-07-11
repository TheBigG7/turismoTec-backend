package com.turismotec.ProyectoGraducacionTurismoTec.model.Dao;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Publicaciones;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPublicacionesDao extends JpaRepository<Publicaciones,Long> {
}
