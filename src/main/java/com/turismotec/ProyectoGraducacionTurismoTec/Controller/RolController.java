package com.turismotec.ProyectoGraducacionTurismoTec.Controller;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Rol;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.IRolService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = {"${frontend.url}"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/public/v1")
public class RolController {

    @Autowired
    private IRolService rolService;

    @GetMapping("/rol")
    public List<Rol> show(){
        return rolService.findAll();
    }

    @GetMapping("/rolParaAsociado")
    public List<Rol> rolParaAsociado(){
        List<Rol> roles = rolService.findAll();
        List<Rol> rolEncontrado = new ArrayList<>();
        for(Rol rol: roles){
            if (rol.getRolNombre().equals("recepcionista")){
                rolEncontrado.add(rol);
            }
        }

        return rolEncontrado;
    }

    @GetMapping("/rol/{idRol}")
    public Rol rolById(@PathVariable Long idRol){
        return rolService.findById(idRol);
    }

}
