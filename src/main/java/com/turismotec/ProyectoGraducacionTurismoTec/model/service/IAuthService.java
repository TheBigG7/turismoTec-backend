package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Auth;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.AuthResponse;

import java.util.List;
import java.util.Optional;

public interface IAuthService {

    public List<Auth> findAll();

    public AuthResponse save(Auth auth);

    public AuthResponse update(Auth auth);

    public AuthResponse updateadmin(Auth auth);

    public AuthResponse updateUsername(Auth auth);

    public Auth findById(Long id);

    public void delete(Long id);

    public Optional<Auth> findbyUserName(String username);

}
