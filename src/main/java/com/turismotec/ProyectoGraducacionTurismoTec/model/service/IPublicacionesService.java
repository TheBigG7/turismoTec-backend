package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Publicaciones;

import java.util.List;

public interface IPublicacionesService {

    public List<Publicaciones> findAll();

    public Publicaciones findById(Long id);

    public Publicaciones save(Publicaciones publicaciones);

    public void delete(Long id);
}
