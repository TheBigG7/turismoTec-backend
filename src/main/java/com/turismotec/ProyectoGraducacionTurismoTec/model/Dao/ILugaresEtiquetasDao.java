package com.turismotec.ProyectoGraducacionTurismoTec.model.Dao;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.LugaresEtiquetas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ILugaresEtiquetasDao extends JpaRepository<LugaresEtiquetas,Long> {

    @Query("SELECT COUNT(le) > 0 FROM LugaresEtiquetas le WHERE le.lugares.id = :idLugar AND le.etiquetas.id = :idEtiqueta")
    boolean existsByLugarAndEtiqueta(@Param("idLugar") Long idLugar, @Param("idEtiqueta") Long idEtiqueta);

}
