package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Foro;

import java.util.List;

public interface IForoService {

    public List<Foro> findAll();

    public Foro findById(Long id);

    public Foro save(Foro foro);

    public void delete(Long id);
}
