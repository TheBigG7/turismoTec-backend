package com.turismotec.ProyectoGraducacionTurismoTec.model.Dao;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ImagenesInicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IImagenesInicioDao extends JpaRepository<ImagenesInicio, Long> {
}
