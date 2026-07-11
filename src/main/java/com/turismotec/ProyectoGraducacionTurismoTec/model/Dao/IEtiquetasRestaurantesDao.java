package com.turismotec.ProyectoGraducacionTurismoTec.model.Dao;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.EtiquetasRestaurantes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEtiquetasRestaurantesDao extends JpaRepository<EtiquetasRestaurantes,Long> {
}
