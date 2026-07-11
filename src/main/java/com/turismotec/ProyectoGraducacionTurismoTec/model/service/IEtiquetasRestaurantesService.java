package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.EtiquetasRestaurantes;

import java.util.List;

public interface IEtiquetasRestaurantesService {

    public List<EtiquetasRestaurantes> findAll();

    public EtiquetasRestaurantes findById(Long id);

    public EtiquetasRestaurantes save(EtiquetasRestaurantes categorias);

    public void delete(Long id);

}
