package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.Jwt.JwtService;
import com.turismotec.ProyectoGraducacionTurismoTec.model.Dao.IAuthDao;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.AuthResponse;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final IAuthDao authDao;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest loginRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        UserDetails user = authDao.findByUsername(loginRequest.getUsername()).orElseThrow();
        String token =jwtService.getToken(user);
        AuthResponse aut = new AuthResponse(token);
        return  aut;
    }
}
