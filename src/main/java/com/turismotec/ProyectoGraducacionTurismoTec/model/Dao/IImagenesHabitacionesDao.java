package com.turismotec.ProyectoGraducacionTurismoTec.model.Dao;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ImagenesHabitaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IImagenesHabitacionesDao extends JpaRepository<ImagenesHabitaciones, Long> {
    @Modifying
    @Query("DELETE FROM ImagenesHabitaciones il WHERE il.habitacion.idHabitacion = :idHabitacion")
    void deleteByHabitacionId(@Param("idHabitacion") Long idHabitacion);
}
