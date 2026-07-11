package com.turismotec.ProyectoGraducacionTurismoTec.Controller;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Hoteles;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ReviewHotel;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ReviewPlaces;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Usuario;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.IHotelesService;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.IReviewHotelService;
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
public class ReviewHotelController {

    @Autowired
    private IReviewHotelService reviewHotelService;
    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private IHotelesService hotelesService;

    @GetMapping("/reviewHotel")
    public List<ReviewHotel> findAll(){
        return reviewHotelService.findAll();
    }

    @GetMapping("/reviewHotelByIdHotel/{idHotel}")
    public List<ReviewHotel> findByHotel(@PathVariable Long idHotel) {

        List<ReviewHotel> reviewHotels = reviewHotelService.findAll();
        List<ReviewHotel> enviarReviewHotels = new ArrayList<>();

        for (ReviewHotel reviewHotel: reviewHotels){
            if (reviewHotel.getHoteles().getIdHotel().equals(idHotel)){
                enviarReviewHotels.add(reviewHotel);
            }
        }
        return enviarReviewHotels;
    }

    @GetMapping("/reviewHotel/{id}")
    public ReviewHotel findById(@PathVariable Long id){
        return reviewHotelService.findById(id);
    }


    @PostMapping("/reviewHotel/{idUsuario}/{idHotel}")
    public ReviewHotel save(@RequestBody ReviewHotel reviewHotel, @PathVariable Long idUsuario, @PathVariable Long idHotel){

        Usuario usuario = usuarioService.findById(idUsuario);
        Hoteles hoteles = hotelesService.findById(idHotel);

        reviewHotel.setHoteles(hoteles);
        reviewHotel.setUsuario(usuario);

        return  reviewHotelService.save(reviewHotel);
    }

    @PutMapping("/reviewHotel/{id}")
    public ReviewHotel update(@RequestBody ReviewHotel reviewHotel, @PathVariable Long id){

        ReviewHotel auxUser =  reviewHotelService.findById(id);
        auxUser.setContenido(reviewHotel.getContenido());
        auxUser.setFechaReview(reviewHotel.getFechaReview());
        auxUser.setCalificacion(reviewHotel.getCalificacion());

        return reviewHotelService.save(auxUser);
    }

    @DeleteMapping("/reviewHotel/{id}")
    public void delete(@PathVariable Long id){
        reviewHotelService.delete(id);
    }
}
