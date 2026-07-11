package com.turismotec.ProyectoGraducacionTurismoTec.Controller;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Habitaciones;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Hoteles;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ReviewHotel;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.IHabitacionesService;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.IHotelesService;
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
public class HabitacionesController {

    @Autowired
    private IHabitacionesService habitacionesService;

    @Autowired
    private IHotelesService hotelesService;


    @GetMapping("/habitacionesDeHotelPublico/{id}")
    public List<Habitaciones> habitacionesDeHotelPublico(@PathVariable Long id){
        List<Habitaciones> habitaciones = habitacionesService.findAll();
        List<Habitaciones> habitacionesDeHotel = new ArrayList<>();

        for (Habitaciones habitacion : habitaciones) {
            if (habitacion.getHoteles().getIdHotel().equals(id) && habitacion.isDisponible()) {
                habitacionesDeHotel.add(habitacion);
            }
        }
        return habitacionesDeHotel;
    }

    @GetMapping("/habitacionesDeHotel/{id}")
    public List<Habitaciones> habitacionesPorIdHotel(@PathVariable Long id){
         List<Habitaciones> habitaciones = habitacionesService.findAll();
         List<Habitaciones> habitacionesDeHotel = new ArrayList<>();

        for (Habitaciones habitacion : habitaciones) {
            if (habitacion.getHoteles().getIdHotel().equals(id)) {
                habitacionesDeHotel.add(habitacion);
            }
        }
         return habitacionesDeHotel;
    }

    @GetMapping("/habitaciones")
    public List<Habitaciones> findAll(){
        return habitacionesService.findAll();
    }

    @GetMapping("/habitaciones/{id}")
    public Habitaciones findById(@PathVariable Long id){
        return habitacionesService.findById(id);
    }


    @PostMapping("/habitaciones/{idHotel}")
    public Habitaciones save(@RequestBody Habitaciones habitaciones,@PathVariable Long idHotel){
        Hoteles hotel = hotelesService.findById(idHotel);
        habitaciones.setHoteles(hotel);
        return  habitacionesService.save(habitaciones);
    }

    @PutMapping("/habitaciones/{id}")
    public Habitaciones update(@RequestBody Habitaciones habitaciones, @PathVariable Long id){

        Habitaciones auxhabitacion =  habitacionesService.findById(id);
        auxhabitacion.setDescripcion(habitaciones.getDescripcion());
        auxhabitacion.setPrecio(habitaciones.getPrecio());
        auxhabitacion.setDisponible(habitaciones.isDisponible());

        return habitacionesService.save(auxhabitacion);
    }

    @DeleteMapping("/habitaciones/{id}")
    public void delete(@PathVariable Long id){
        habitacionesService.delete(id);
    }
}
