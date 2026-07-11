package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.LugaresEtiquetas;

import java.util.List;

public interface ILugaresEtiquetasService {

    public List<LugaresEtiquetas> findAll();

    public LugaresEtiquetas findById(Long id);

    public LugaresEtiquetas save(LugaresEtiquetas lugaresEtiquetas);

    public void delete(Long id);

    public boolean existsByLugarAndEtiqueta(Long idLugar, Long idEtiqueta);
}
