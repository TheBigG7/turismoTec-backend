package com.turismotec.ProyectoGraducacionTurismoTec.Controller;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Auth;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.AuthResponse;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Auth_rol;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"${frontend.url}"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class AuthController {

    @Autowired
    private IAuthService authService;

    @GetMapping("/auth")
    public List<Auth> index(){
        return authService.findAll();
    }

    @GetMapping("/auth/{id}")
    public Auth show(@PathVariable Long id) {
        return authService.findById(id);
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> create(@RequestBody Auth auth) {
        return ResponseEntity.ok(authService.save(auth));
    }

    @PutMapping("/auth/{idAuth}")
    public ResponseEntity<AuthResponse> update(@PathVariable Long idAuth, @RequestBody Auth authRequest) {
        Auth auxAuth = authService.findById(idAuth);
        if (auxAuth == null) {
            return ResponseEntity.notFound().build(); // Devuelve 404 si el usuario no existe
        }
        auxAuth.setUsername(authRequest.getUsername());
        auxAuth.setPassword(authRequest.getPassword());
        return ResponseEntity.ok(authService.updateadmin(auxAuth));
    }

    @PutMapping("/authUserName/{idAuth}")
    public ResponseEntity<AuthResponse> updateUsername(@PathVariable Long idAuth, @RequestBody Auth authRequest) {
        Auth auxAuth = authService.findById(idAuth);
        auxAuth.setUsername(authRequest.getUsername());
        return ResponseEntity.ok(authService.updateUsername(auxAuth));
    }

    @PutMapping("/authPassword/{idAuth}")
    public ResponseEntity<AuthResponse> updatePassword(@PathVariable Long idAuth, @RequestBody Auth authRequest) {
        Auth auxAuth = authService.findById(idAuth);
        auxAuth.setPassword(authRequest.getPassword());
        return ResponseEntity.ok(authService.update(auxAuth));
    }

    @DeleteMapping("/auth/{id}")
    public void delete(@PathVariable Long id) {
        authService.delete(id);
    }

    @GetMapping("/authGet/{nombre}")
    public Optional<Auth> usuarioOptional(@PathVariable String nombre){
        return authService.findbyUserName(nombre);
    }

    @GetMapping("/authGetId/{nombre}")
    public Long idUser(@PathVariable String nombre){
        Optional<Auth> auxAuth = authService.findbyUserName(nombre);
        return auxAuth.get().getId_usuario();
    }


    @GetMapping("/authByIdUser/{idUsuario}")
    public Auth auth_rolByIdUser(@PathVariable Long idUsuario){
        List<Auth> enviarAut = authService.findAll();
        Auth enviarAutEnviar = new Auth();
        for (Auth authRol:enviarAut){
            if(authRol.getId_usuario().equals(idUsuario)){
                enviarAutEnviar = authRol;
            }
        }
        return enviarAutEnviar;
    }
}
