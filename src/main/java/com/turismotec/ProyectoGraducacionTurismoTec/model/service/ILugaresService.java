package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Lugares;

import java.util.List;

public interface ILugaresService {

    public List<Lugares> findAll();

    public Lugares findById(Long id);

    public Lugares save(Lugares lugares);

    public void delete(Long id);

    public List<Lugares> obtenerLugaresAprobadosAndVisualizacionTrue();

    List<Lugares> obtenerLugaresCreadoPorAdmin();
}
