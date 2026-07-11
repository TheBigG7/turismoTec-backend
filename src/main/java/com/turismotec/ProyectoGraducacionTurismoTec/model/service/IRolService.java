package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Rol;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Usuario;

import java.util.List;

public interface IRolService {

    public List<Rol> findAll();

    public Rol findById(Long id);

    public Rol save(Rol rol);

    public void delete(Long id);
}
