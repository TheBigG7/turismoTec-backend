package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.EtiquetasLugares;

import java.util.List;

public interface IEtiquetasLugaresService {

    public List<EtiquetasLugares> findAll();

    public EtiquetasLugares findById(Long id);

    public EtiquetasLugares save(EtiquetasLugares categorias);

    public void delete(Long id);
}
