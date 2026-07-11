package com.turismotec.ProyectoGraducacionTurismoTec.model.Dao;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Foro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IForoDao extends JpaRepository<Foro,Long> {
}
