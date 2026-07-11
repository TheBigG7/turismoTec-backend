package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Habitaciones;

import java.util.List;

public interface IHabitacionesService {

    public List<Habitaciones> findAll();

    public Habitaciones findById(Long id);

    public Habitaciones save(Habitaciones habitaciones);

    public void delete(Long id);
}
