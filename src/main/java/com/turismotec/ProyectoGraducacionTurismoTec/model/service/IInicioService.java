package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Inicio;

import java.util.List;

public interface IInicioService {
    public List<Inicio> findAll();

    public Inicio findById(Long id);

    public Inicio save(Inicio inicio);

    public void delete(Long id);
}
