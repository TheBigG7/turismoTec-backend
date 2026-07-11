package com.turismotec.ProyectoGraducacionTurismoTec.model.Dao;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ImagenesRestaurantes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IImagenesRestaurantesDao extends JpaRepository<ImagenesRestaurantes, Long> {
    @Modifying
    @Query("DELETE FROM ImagenesRestaurantes il WHERE il.restaurante.idRestaurante = :idRestaurante")
    void deleteByRestauranteId(@Param("idRestaurante") Long idRestaurante);
}
