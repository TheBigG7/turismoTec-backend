package com.turismotec.ProyectoGraducacionTurismoTec.model.Dao;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Comentarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IComentariosDao extends JpaRepository<Comentarios,Long> {
}
