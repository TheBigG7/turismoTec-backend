package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Mesas;

import java.util.List;

public interface IMesasService {

    public List<Mesas> findAll();

    public Mesas findById(Long id);

    public Mesas save(Mesas mesas);

    public void delete(Long id);
}
