package com.turismotec.ProyectoGraducacionTurismoTec.model.Dao;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Hoteles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IHotelesDao extends JpaRepository<Hoteles,Long> {

    @Query("SELECT COUNT(*) FROM Hoteles WHERE usuario.id_Usuario = :id_usuario")
    public int cantidadDeHotelesCreados(@Param("id_usuario") Long idUsuario);

    @Query("SELECT COUNT(*) FROM Hoteles")
    public int cantidadDeHotelesCreadosParaAdmin();
}
