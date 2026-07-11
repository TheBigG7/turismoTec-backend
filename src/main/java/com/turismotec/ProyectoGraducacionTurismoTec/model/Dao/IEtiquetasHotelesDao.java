package com.turismotec.ProyectoGraducacionTurismoTec.model.Dao;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.EtiquetasHoteles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEtiquetasHotelesDao extends JpaRepository<EtiquetasHoteles,Long> {
}
