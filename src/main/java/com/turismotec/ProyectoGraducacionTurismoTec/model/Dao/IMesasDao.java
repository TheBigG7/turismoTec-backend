package com.turismotec.ProyectoGraducacionTurismoTec.model.Dao;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Mesas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMesasDao extends JpaRepository<Mesas,Long> {
}
