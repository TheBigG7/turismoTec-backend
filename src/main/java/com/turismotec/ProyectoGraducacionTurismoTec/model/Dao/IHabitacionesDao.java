package com.turismotec.ProyectoGraducacionTurismoTec.model.Dao;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Habitaciones;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHabitacionesDao extends JpaRepository<Habitaciones,Long> {

}
