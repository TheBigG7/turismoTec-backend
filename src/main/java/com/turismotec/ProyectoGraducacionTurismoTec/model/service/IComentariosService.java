package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Comentarios;

import java.util.List;

public interface IComentariosService {

    public List<Comentarios> findAll();

    public Comentarios findById(Long id);

    public Comentarios save(Comentarios comentarios);

    public void delete(Long id);
}
