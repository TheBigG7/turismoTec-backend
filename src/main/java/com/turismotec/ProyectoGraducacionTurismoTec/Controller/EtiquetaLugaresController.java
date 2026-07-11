package com.turismotec.ProyectoGraducacionTurismoTec.Controller;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.EtiquetasLugares;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.IEtiquetasLugaresService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"${frontend.url}"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/public/v1")

public class EtiquetaLugaresController {

    @Autowired
    private IEtiquetasLugaresService etiquetasService;

    @GetMapping("/etiquetasLugares")
    public List<EtiquetasLugares> findAll(){
        return etiquetasService.findAll();
    }

    @GetMapping("/etiquetasLugares/{id}")
    public EtiquetasLugares findById(@PathVariable Long id){
        return etiquetasService.findById(id);
    }

    @PostMapping("/etiquetasLugares")
    public EtiquetasLugares save(@RequestBody EtiquetasLugares categorias){
        return  etiquetasService.save(categorias);
    }

    @PutMapping("/etiquetasLugares/{id}")
    public EtiquetasLugares update(@RequestBody EtiquetasLugares categorias, @PathVariable Long id){
        EtiquetasLugares auxUser =  etiquetasService.findById(id);
        auxUser.setEtiqueta(categorias.getEtiqueta());
        return etiquetasService.save(auxUser);
    }

    @DeleteMapping("/etiquetasLugares/{id}")
    public void delete(@PathVariable Long id){
        etiquetasService.delete(id);
    }
}
