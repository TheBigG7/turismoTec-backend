package com.turismotec.ProyectoGraducacionTurismoTec.model.Dao;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ImagenesHoteles;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ImagenesMesas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IImagenesMesaDao extends JpaRepository<ImagenesMesas, Long> {
    @Modifying
    @Query("DELETE FROM ImagenesMesas il WHERE il.mesas.idMesa = :idMesa")
    void deleteByMesasId(@Param("idMesa") Long idMesa);
}
