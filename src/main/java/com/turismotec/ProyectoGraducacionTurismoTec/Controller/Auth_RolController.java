package com.turismotec.ProyectoGraducacionTurismoTec.Controller;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.*;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = {"${frontend.url}"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class Auth_RolController {

    @Autowired
    private Auth_RolServiceImplement serviceImplement;

    @Autowired
    private IAuthService authService;

    @Autowired
    private IRolService rolService;

    @GetMapping("/auth_rol")
    public List<Auth_rol> index(){
        return serviceImplement.findAll();
    }

    @PostMapping("/auth_rol/{idAuth}/{idRol}")
    public Auth_rol create(@RequestBody Auth_rol auth_rol, @PathVariable Long idAuth, @PathVariable Long idRol){
        Auth enviarAut = authService.findById(idAuth);
        Rol enviarRol = rolService.findById(idRol);

        if (serviceImplement.existsByAuthAndRol(idAuth,idRol)){
            return null;
        }

        auth_rol.setAuth(enviarAut);
        auth_rol.setRol(enviarRol);

        return serviceImplement.save(auth_rol);
    }

    @DeleteMapping("/auth_rol/{idRol}")
    public void delete(@PathVariable Long idRol){
        serviceImplement.delete(idRol);
    }

    @GetMapping("/rolesByIdAuth/{idAuth}")
    public List<Auth_rol> rolesByIdAuth(@PathVariable Long idAuth){
        List<Auth_rol> authRols = serviceImplement.findAll();
        List<Auth_rol> authRolsEnviar = new ArrayList<>();
        for (Auth_rol authRol: authRols){
            if(authRol.getAuth().getId_auth().equals(idAuth)){
                authRolsEnviar.add(authRol);
            }
        }
        return authRolsEnviar;
    }

}
