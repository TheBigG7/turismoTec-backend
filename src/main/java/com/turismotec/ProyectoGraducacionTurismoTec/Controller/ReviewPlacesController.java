package com.turismotec.ProyectoGraducacionTurismoTec.Controller;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Lugares;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ReviewHotel;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ReviewPlaces;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Usuario;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.ILugaresService;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.IReviewHotelService;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.IReviewPlacesService;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = {"${frontend.url}"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/public/v1")
public class ReviewPlacesController {

    @Autowired
    private IReviewPlacesService reviewPlacesService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private ILugaresService lugaresService;

    @GetMapping("/reviewPlaces")
    public List<ReviewPlaces> findAll(){
        return reviewPlacesService.findAll();
    }

    @GetMapping("/reviewPlacesByIdLugar/{idLugar}")
    public List<ReviewPlaces> findByLugar(@PathVariable Long idLugar) {

        List<ReviewPlaces> reviewPlaces = reviewPlacesService.findAll();
        List<ReviewPlaces> enviarReviewPlaces = new ArrayList<>();

        for (ReviewPlaces reviewPlaces1: reviewPlaces){
            if (reviewPlaces1.getLugares().getIdLugares().equals(idLugar)){
                enviarReviewPlaces.add(reviewPlaces1);
            }
        }
        return enviarReviewPlaces;
    }


    @GetMapping("/reviewPlaces/{id}")
    public ReviewPlaces findById(@PathVariable Long id){
        return reviewPlacesService.findById(id);
    }

    @PostMapping("/reviewPlaces/{idUsuario}/{idLugar}")
    public ReviewPlaces save(@RequestBody ReviewPlaces reviewPlaces, @PathVariable Long idUsuario, @PathVariable Long idLugar){

        Usuario usuario = usuarioService.findById(idUsuario);
        Lugares lugares = lugaresService.findById(idLugar);

        reviewPlaces.setUsuario(usuario);
        reviewPlaces.setLugares(lugares);

        return  reviewPlacesService.save(reviewPlaces);
    }

    @PutMapping("/reviewPlaces/{id}")
    public ReviewPlaces update(@RequestBody ReviewHotel reviewPlaces, @PathVariable Long id){

        ReviewPlaces auxUser =  reviewPlacesService.findById(id);
        auxUser.setContenido(reviewPlaces.getContenido());
        auxUser.setFechaReview(reviewPlaces.getFechaReview());
        auxUser.setCalificacion(reviewPlaces.getCalificacion());

        return reviewPlacesService.save(auxUser);
    }

    @DeleteMapping("/reviewPlaces/{id}")
    public void delete(@PathVariable Long id){
        reviewPlacesService.delete(id);
    }
}
