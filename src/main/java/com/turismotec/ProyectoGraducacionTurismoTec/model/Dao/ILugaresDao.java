package com.turismotec.ProyectoGraducacionTurismoTec.model.Dao;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Lugares;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILugaresDao extends JpaRepository<Lugares,Long> {
    List<Lugares> findByAprobadoTrueAndVisualizacionTrue();
    List<Lugares> findBycreadoPorTrue();
}
