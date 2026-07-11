package com.turismotec.ProyectoGraducacionTurismoTec.model.Dao;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ImagenesLugar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IImagenesLugaresDao extends JpaRepository<ImagenesLugar, Long> {
    @Modifying
    @Query("DELETE FROM ImagenesLugar il WHERE il.lugares.idLugares = :idLugar")
    void deleteByLugaresId(@Param("idLugar") Long idLugar);

}
