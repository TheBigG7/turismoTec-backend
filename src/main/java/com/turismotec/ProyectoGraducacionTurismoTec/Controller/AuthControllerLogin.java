package com.turismotec.ProyectoGraducacionTurismoTec.Controller;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Auth;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.AuthResponse;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.LoginRequest;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.AuthService;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.AuthServiceImplement;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = {"${frontend.url}"})
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class AuthControllerLogin {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }


}
