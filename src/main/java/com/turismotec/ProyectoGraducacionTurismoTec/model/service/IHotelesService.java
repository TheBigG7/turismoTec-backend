package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Hoteles;


import java.util.List;

public interface IHotelesService {

    public List<Hoteles> findAll();

    public Hoteles findById(Long id);

    public Hoteles save(Hoteles hoteles);

    public void delete(Long id);

    public int cantidadDeHotelesCreados(Long id);

    public int cantidadDeHotelesCreadosParaAdmin();
}
