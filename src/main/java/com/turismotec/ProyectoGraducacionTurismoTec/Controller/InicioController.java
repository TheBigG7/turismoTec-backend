package com.turismotec.ProyectoGraducacionTurismoTec.Controller;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Inicio;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.IInicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = {"${frontend.url}"})
@RestController
@RequestMapping("/public/v1")
public class InicioController {

    @Autowired
    private IInicioService inicioService;

    @GetMapping("/inicio")
    public List<Inicio> findAll(){
        return inicioService.findAll();
    }

    @GetMapping("/inicio/{id}")
    public Inicio findById(@PathVariable Long id){
        return inicioService.findById(id);
    }

    @PostMapping("/inicio")
    public Inicio save(@RequestBody Inicio inicio){
        return  inicioService.save(inicio);
    }

    @PutMapping("/inicio/{id}")
    public Inicio update(@RequestBody Inicio inicio, @PathVariable Long id){

        Inicio auxInicio =  inicioService.findById(id);
        auxInicio.setTitulo(inicio.getTitulo());
        auxInicio.setDescripcion(inicio.getDescripcion());
        auxInicio.setUsuario(inicio.getUsuario());

        return inicioService.save(auxInicio);
    }

    @DeleteMapping("/inicio/{id}")
    public void delete(@PathVariable Long id){
        inicioService.delete(id);
    }
}
