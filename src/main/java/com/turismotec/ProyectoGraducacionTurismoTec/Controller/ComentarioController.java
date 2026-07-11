package com.turismotec.ProyectoGraducacionTurismoTec.Controller;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.*;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.IComentariosService;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.IPublicacionesService;
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
public class ComentarioController {

    @Autowired
    private IComentariosService comentariosService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IPublicacionesService publicacionesService;

    @GetMapping("/comentariosDePublicacion/{idPublicacion}")
    public List<Comentarios> hotelesDeLugar(@PathVariable Long idPublicacion){
        List<Comentarios> ComentariosList = comentariosService.findAll();
        List<Comentarios> ComentariosDePublicaciones = new ArrayList<>();

        for (Comentarios comentarios: ComentariosList){
            if(comentarios.getPublicaciones().getIdPublicaciones().equals(idPublicacion)){
                ComentariosDePublicaciones.add(comentarios);
            }
        }
        return ComentariosDePublicaciones;
    }

    @GetMapping("/comentarios")
    public List<Comentarios> findAll(){
        return comentariosService.findAll();
    }

    @GetMapping("/comentarios/{id}")
    public Comentarios findById(@PathVariable Long id){
        return comentariosService.findById(id);
    }

    @PostMapping("/comentarios/{idUsuario}/{idPublicacion}")
    public Comentarios save(@RequestBody Comentarios comentarios, @PathVariable Long idUsuario, @PathVariable Long idPublicacion){
        Usuario usuario = usuarioService.findById(idUsuario);
        Publicaciones publicaciones = publicacionesService.findById(idPublicacion);
        comentarios.setUsuario(usuario);
        comentarios.setPublicaciones(publicaciones);
        return  comentariosService.save(comentarios);
    }

    @PutMapping("/comentarios/{id}")
    public Comentarios update(@RequestBody Comentarios reviewHotel, @PathVariable Long id){

        Comentarios auxUser =  comentariosService.findById(id);
        auxUser.setContenido(reviewHotel.getContenido());
        return comentariosService.save(auxUser);
    }

    @DeleteMapping("/comentarios/{id}")
    public void delete(@PathVariable Long id){
        comentariosService.delete(id);
    }
}
