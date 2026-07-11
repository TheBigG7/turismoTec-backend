package com.turismotec.ProyectoGraducacionTurismoTec.model.Dao;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IRestauranteDao extends JpaRepository<Restaurante,Long> {

    @Query("SELECT COUNT(*) FROM Restaurante WHERE usuario.id_Usuario = :id_usuario")
    public int cantidadDeRestaurantesCreados(@Param("id_usuario") Long idUsuario);

    @Query("SELECT COUNT(*) FROM Restaurante")
    public int cantidadDeRestaurantesCreadosParaAdmin();
}
