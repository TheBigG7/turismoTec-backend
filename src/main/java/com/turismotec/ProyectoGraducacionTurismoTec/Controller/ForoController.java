package com.turismotec.ProyectoGraducacionTurismoTec.Controller;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Foro;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ReviewHotel;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.IForoService;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.IReviewHotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"${frontend.url}"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/public/v1")
public class ForoController {

    @Autowired
    private IForoService foroService;

    @GetMapping("/foro")
    public List<Foro> findAll(){
        return foroService.findAll();
    }

    @GetMapping("/foro/{id}")
    public Foro findById(@PathVariable Long id){
        return foroService.findById(id);
    }

    @PostMapping("/foro")
    public Foro save(@RequestBody Foro foro){
        return  foroService.save(foro);
    }

    @PutMapping("/foro/{id}")
    public Foro update(@RequestBody Foro foro, @PathVariable Long id){

        Foro auxUser = foroService.findById(id);
        auxUser.setTitulo(foro.getTitulo());
        auxUser.setDescripcion(foro.getDescripcion());

        return foroService.save(auxUser);
    }

    @DeleteMapping("/foro/{id}")
    public void delete(@PathVariable Long id){
        foroService.delete(id);
    }
}
