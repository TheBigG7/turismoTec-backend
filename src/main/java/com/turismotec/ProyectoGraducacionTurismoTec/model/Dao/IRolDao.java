package com.turismotec.ProyectoGraducacionTurismoTec.model.Dao;


import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRolDao extends JpaRepository<Rol, Long> {
}
