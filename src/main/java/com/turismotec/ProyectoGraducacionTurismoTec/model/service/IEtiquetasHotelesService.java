package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.EtiquetasHoteles;

import java.util.List;

public interface IEtiquetasHotelesService {

    public List<EtiquetasHoteles> findAll();

    public EtiquetasHoteles findById(Long id);

    public EtiquetasHoteles save(EtiquetasHoteles categorias);

    public void delete(Long id);

}
