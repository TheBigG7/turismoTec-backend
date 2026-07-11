package com.turismotec.ProyectoGraducacionTurismoTec.Controller;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.EtiquetasHoteles;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.EtiquetasRestaurantes;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Hoteles;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Restaurante;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.IEtiquetasRestaurantesService;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.IRestauranteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = {"${frontend.url}"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/public/v1")
public class EtiquetasRestaurantesController {

    @Autowired
    private IEtiquetasRestaurantesService etiquetasService;

    @Autowired
    private IRestauranteService restaurantesService;

    @GetMapping("/etiquetasRestaurantes")
    public List<EtiquetasRestaurantes> findAll() {
        return etiquetasService.findAll();
    }

    @GetMapping("/etiquetasRestaurantes/{id}")
    public EtiquetasRestaurantes findById(@PathVariable Long id) {
        return etiquetasService.findById(id);
    }

    @PostMapping("/etiquetasRestaurantes")
    public EtiquetasRestaurantes save(@RequestBody EtiquetasRestaurantes categorias) {
        return etiquetasService.save(categorias);
    }

    @PostMapping("/etiquetasRestaurantesRestaurante/{idRestaurante}/{opcion}")
    public EtiquetasRestaurantes saveRestaurante(@RequestBody EtiquetasRestaurantes etiqueta, @PathVariable Long idRestaurante, @PathVariable Long opcion) {
        Restaurante restaurante = restaurantesService.findById(idRestaurante);

        List<Restaurante> restaunrate = restaurantesService.findAll();
        List<Restaurante> restaurantesActuales = new ArrayList<>();
        for (Restaurante r : restaunrate){
            for (EtiquetasRestaurantes er:r.getEtiquetasRestaurantes()){
                if (er.getIdEtiquetaRestaurante().equals(etiqueta.getIdEtiquetaRestaurante())){
                    restaurantesActuales.add(r);
                }
            }
        }

        if (opcion == 1) {
            restaurantesActuales.remove(restaurante);
        } else {
            restaurantesActuales.add(restaurante);
        }

        etiqueta.setRestaurantes(restaurantesActuales);
        return etiquetasService.save(etiqueta);
    }

    @PutMapping("/etiquetasRestaurantes/{id}")
    public EtiquetasRestaurantes update(@RequestBody EtiquetasRestaurantes etiquetas, @PathVariable Long id) {
        EtiquetasRestaurantes auxEtiquetaRestaurantes = etiquetasService.findById(id);
        auxEtiquetaRestaurantes.setEtiqueta(etiquetas.getEtiqueta());
        auxEtiquetaRestaurantes.setRestaurantes(etiquetas.getRestaurantes());
        return etiquetasService.save(auxEtiquetaRestaurantes);
    }

    @DeleteMapping("/etiquetasRestaurantes/{id}")
    public void delete(@PathVariable Long id) {
        etiquetasService.delete(id);
    }

    @GetMapping("/etiquetasDeRestauranteFilter/{idRestaurante}")
    public List<EtiquetasRestaurantes> etiquetasDeRestaurante(@PathVariable Long idRestaurante) {
        List<EtiquetasRestaurantes> etiquetasRestaurantesList = etiquetasService.findAll();
        List<EtiquetasRestaurantes> etiquetaDeRestaurante = new ArrayList<>();

        for (EtiquetasRestaurantes er : etiquetasRestaurantesList) {
            for (Restaurante r : er.getRestaurantes()) {
                if (r.getIdRestaurante().equals(idRestaurante)) {
                    etiquetaDeRestaurante.add(er);
                }
            }
        }
        return etiquetaDeRestaurante;
    }
}
