package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.Jwt.JwtService;
import com.turismotec.ProyectoGraducacionTurismoTec.model.Dao.IAuthDao;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Auth;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.AuthResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Data
@RequiredArgsConstructor
@Service
public class AuthServiceImplement implements IAuthService{

    @Autowired
    private IAuthDao authDao;

    private final JwtService jwtservice;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional(readOnly = true)
    public List<Auth> findAll() {
        return authDao.findAll();
    }

    @Override
    @Transactional
    public AuthResponse save(Auth auth) {
        Auth authSave = Auth.builder()
                .username(auth.getUsername())
                .password(passwordEncoder.encode(auth.getPassword()))
                .id_usuario(auth.getId_usuario())
                .build();

        authDao.save(authSave);

        return AuthResponse.builder()
                .token(jwtservice.getToken(authSave))
                .build();
    }

    @Transactional
    public AuthResponse updateadmin(Auth auth) {
        // Buscar el registro existente
        Auth existingAuth = authDao.findById(auth.getId_auth()).orElseThrow(() ->
                new RuntimeException("No se encontró el usuario con ID: " + auth.getId_usuario()));
        existingAuth.setUsername(auth.getUsername());
        existingAuth.setPassword(passwordEncoder.encode(auth.getPassword()));
        authDao.save(existingAuth);

        return AuthResponse.builder()
                .token(jwtservice.getToken(existingAuth))
                .build();
    }


    @Transactional
    public AuthResponse update(Auth auth) {
        // Buscar el registro existente
        Auth existingAuth = authDao.findById(auth.getId_auth()).orElseThrow(() ->
                new RuntimeException("No se encontró el usuario con ID: " + auth.getId_usuario()));

        existingAuth.setPassword(passwordEncoder.encode(auth.getPassword()));
        authDao.save(existingAuth);

        return AuthResponse.builder()
                .token(jwtservice.getToken(existingAuth))
                .build();
    }

    @Transactional
    public AuthResponse updateUsername(Auth auth) {
        // Buscar el registro existente
        Auth existingAuth = authDao.findById(auth.getId_auth()).orElseThrow(() ->
                new RuntimeException("No se encontró el usuario con ID: " + auth.getId_usuario())
        );

        // Actualizar los campos necesarios
        existingAuth.setUsername(auth.getUsername());

        // Guardar el registro actualizado
        authDao.save(existingAuth);

        // Generar el token con la entidad actualizada
        return AuthResponse.builder()
                .token(jwtservice.getToken(existingAuth))
                .build();
    }


    @Override
    @Transactional(readOnly = true)
    public Auth findById(Long id) {
        return authDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        authDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Auth> findbyUserName(String username) {
        return authDao.findByUsername(username);
    }

}
