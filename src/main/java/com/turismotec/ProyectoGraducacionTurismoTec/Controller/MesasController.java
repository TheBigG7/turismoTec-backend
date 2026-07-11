package com.turismotec.ProyectoGraducacionTurismoTec.Controller;


import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.*;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.IMesasService;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.IRestauranteService;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.IReviewHotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = {"${frontend.url}"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/public/v1")
public class MesasController {

    @Autowired
    private IMesasService mesasService;

    @Autowired
    private IRestauranteService restauranteService;

    @GetMapping("/mesasDeRestaurante/{idRestaurante}")
    public List<Mesas> habitacionesPorIdHotel(@PathVariable Long idRestaurante){
        List<Mesas> mesas = mesasService.findAll();
        List<Mesas> mesasDeRestaurante = new ArrayList<>();

        for (Mesas mesas1 : mesas) {
            if (mesas1.getRestaurante().getIdRestaurante().equals(idRestaurante)) {
                mesasDeRestaurante.add(mesas1);
            }
        }
        System.out.println("MESAS");
        return mesasDeRestaurante;
    }

    @GetMapping("/mesas")
    public List<Mesas> findAll(){
        return mesasService.findAll();
    }

    @GetMapping("/mesas/{id}")
    public Mesas findById(@PathVariable Long id){
        return mesasService.findById(id);
    }

    @PostMapping("/mesas")
    public Mesas save(@RequestBody Mesas mesas){
        return  mesasService.save(mesas);
    }

    @PostMapping("/mesas/{idRestaurante}")
    public Mesas save(@RequestBody Mesas mesas,@PathVariable Long idRestaurante){
        Restaurante restaurante = restauranteService.findById(idRestaurante);
        mesas.setRestaurante(restaurante);
        return  mesasService.save(mesas);
    }

    @PutMapping("/mesas/{id}")
    public Mesas update(@RequestBody Mesas mesas, @PathVariable Long id) {

        Mesas auxUser = mesasService.findById(id);
        auxUser.setCapacidad(mesas.getCapacidad());
        auxUser.setNumeroMesa(mesas.getNumeroMesa());
        auxUser.setDisponibilidad(mesas.isDisponibilidad());

        return mesasService.save(auxUser);
    }

    @DeleteMapping("/mesas/{id}")
    public void delete(@PathVariable Long id){
        mesasService.delete(id);
    }
}
