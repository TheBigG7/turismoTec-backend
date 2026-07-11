package com.turismotec.ProyectoGraducacionTurismoTec.Controller;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Restaurante;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ReviewHotel;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ReviewRestaurante;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Usuario;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.IRestauranteService;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.IReviewHotelService;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.IReviewRestauranteService;
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
public class ReviewRestauranteController {

    @Autowired
    private IReviewRestauranteService reviewRestauranteService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IRestauranteService restauranteService;

    @GetMapping("/reviewRestaurante")
    public List<ReviewRestaurante> findAll(){
        return reviewRestauranteService.findAll();
    }

    @GetMapping("/reviewRestauranteByIdRestaurante/{idRestaurante}")
    public List<ReviewRestaurante> findByRestaurante(@PathVariable Long idRestaurante) {

        List<ReviewRestaurante> reviewRestaurantes = reviewRestauranteService.findAll();
        List<ReviewRestaurante> enviarReviewRestaurante = new ArrayList<>();

        for (ReviewRestaurante reviewRestaurante: reviewRestaurantes){
            if (reviewRestaurante.getRestaurante().getIdRestaurante().equals(idRestaurante)){
                enviarReviewRestaurante.add(reviewRestaurante);
            }
        }
        return enviarReviewRestaurante;
    }

    @GetMapping("/reviewRestaurante/{id}")
    public ReviewRestaurante findById(@PathVariable Long id){
        return reviewRestauranteService.findById(id);
    }

    @PostMapping("/reviewRestaurante/{idUsuario}/{idRestaurante}")
    public ReviewRestaurante save(@RequestBody ReviewRestaurante reviewRestaurante, @PathVariable Long idUsuario, @PathVariable Long idRestaurante){

        Usuario usuario = usuarioService.findById(idUsuario);
        Restaurante restaurante = restauranteService.findById(idRestaurante);

        reviewRestaurante.setRestaurante(restaurante);
        reviewRestaurante.setUsuario(usuario);

        return  reviewRestauranteService.save(reviewRestaurante);
    }

    @PutMapping("/reviewRestaurante/{id}")
    public ReviewRestaurante update(@RequestBody ReviewRestaurante reviewRestaurante, @PathVariable Long id){

        ReviewRestaurante auxUser =  reviewRestauranteService.findById(id);
        auxUser.setContenido(reviewRestaurante.getContenido());
        auxUser.setFechaReview(reviewRestaurante.getFechaReview());
        auxUser.setCalificacion(reviewRestaurante.getCalificacion());

        return reviewRestauranteService.save(auxUser);
    }

    @DeleteMapping("/reviewRestaurante/{id}")
    public void delete(@PathVariable Long id){
        reviewRestauranteService.delete(id);
    }
}
