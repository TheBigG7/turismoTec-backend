package com.turismotec.ProyectoGraducacionTurismoTec.Controller;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.EtiquetasHoteles;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Hoteles;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.IEtiquetasHotelesService;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.IHotelesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = {"${frontend.url}"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/public/v1")

public class EtiquetasHotelesController {

    @Autowired
    private IEtiquetasHotelesService etiquetasService;

    @Autowired
    private IHotelesService hotelService;

    @GetMapping("/etiquetasHoteles")
    public List<EtiquetasHoteles> findAll() {
        return etiquetasService.findAll();
    }

    @GetMapping("/etiquetasHoteles/{id}")
    public EtiquetasHoteles findById(@PathVariable Long id) {
        return etiquetasService.findById(id);
    }

    @PostMapping("/etiquetasHoteles")
    public EtiquetasHoteles save(@RequestBody EtiquetasHoteles categorias) {
        return etiquetasService.save(categorias);
    }

//    @GetMapping("/hotelesPorHetiqueta/{idEtiqueta}")
//    public List<Hoteles> hotelesPorEtiquetas(@PathVariable Long idEtiqueta) {
//        List<Hoteles> hotelesList = hotelService.findAll();
//        List<Hoteles> hotelesPorEtiqueta = new ArrayList<>();
//
//        for (Hoteles hoteles : hotelesList) {
//            for (EtiquetasHoteles eh : hoteles.getEtiquetasHoteles())
//                if (eh.getIdEtiquetaHoteles().equals(idEtiqueta)) {
//                    hotelesPorEtiqueta.add(hoteles);
//                }
//        }
//        return hotelesPorEtiqueta;
//    }

    @PostMapping("/etiquetasHotelesHotel/{idHotel}/{opcion}")
    public EtiquetasHoteles saveHotel(@RequestBody EtiquetasHoteles etiqueta, @PathVariable Long idHotel, @PathVariable Long opcion) {
        Hoteles hotel = hotelService.findById(idHotel);

        List<Hoteles> hoteles = hotelService.findAll();
        List<Hoteles> hotelesActuales = new ArrayList<>();
        for (Hoteles h : hoteles){
            for (EtiquetasHoteles eh:h.getEtiquetasHoteles()){
                if (eh.getIdEtiquetaHoteles().equals(etiqueta.getIdEtiquetaHoteles())){
                    hotelesActuales.add(h);
                }
            }
        }

        if (opcion == 1) {
            // Eliminar el hotel de la lista
            hotelesActuales.remove(hotel);
        } else {
            // Agregar el hotel a la lista
            hotelesActuales.add(hotel);
        }

        // Establecer la lista actualizada en el objeto EtiquetaHoteles
        etiqueta.setHoteles(hotelesActuales);
        return etiquetasService.save(etiqueta);
    }

    @PutMapping("/etiquetasHoteles/{id}")
    public EtiquetasHoteles update(@RequestBody EtiquetasHoteles etiquetas, @PathVariable Long id) {
        EtiquetasHoteles auxEtiquetaHoteles = etiquetasService.findById(id);
        auxEtiquetaHoteles.setEtiqueta(etiquetas.getEtiqueta());
        auxEtiquetaHoteles.setHoteles(etiquetas.getHoteles());
        return etiquetasService.save(auxEtiquetaHoteles);
    }

    @DeleteMapping("/etiquetasHoteles/{id}")
    public void delete(@PathVariable Long id) {
        etiquetasService.delete(id);
    }

    @GetMapping("/etiquetasDeHotelFilter/{idHotel}")
    public List<EtiquetasHoteles> etiquetasDeHotel(@PathVariable Long idHotel) {
        List<EtiquetasHoteles> etiquetasHotelesList = etiquetasService.findAll();
        List<EtiquetasHoteles> etiquetaDeHotel = new ArrayList<>();

        for (EtiquetasHoteles eh : etiquetasHotelesList) {
            for (Hoteles h : eh.getHoteles()) {
                if (h.getIdHotel().equals(idHotel)) {
                    etiquetaDeHotel.add(eh);
                }
            }
        }

        return etiquetaDeHotel;
    }
}
