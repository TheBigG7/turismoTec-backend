package com.turismotec.ProyectoGraducacionTurismoTec.Controller;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.EtiquetasHoteles;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Hoteles;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Lugares;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Usuario;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.IHotelesService;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.ILugaresService;
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
public class HotelController {

    @Autowired
    private IHotelesService hotelesService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private ILugaresService lugaresService;

//    @GetMapping("/hotelesPublicos")
//    public List<Hoteles> obtenerLugaresAprobados() {
//        return hotelesService.obtenerLugaresAprobadosAndVisualizacionTrue();
//    }

    @GetMapping("/hotel")
    public List<Hoteles> findAll() {
        return hotelesService.findAll();
    }

    @GetMapping("/hotel/{id}")
    public Hoteles findById(@PathVariable Long id) {
        return hotelesService.findById(id);
    }

    @GetMapping("/hotelesByIdUser/{idUser}")
    public List<Hoteles> findByIdUser(@PathVariable Long idUser) {
        List<Hoteles> hotelesList = hotelesService.findAll();
        List<Hoteles> enviarHoteles = new ArrayList<>();

        for (Hoteles hoteles : hotelesList) {
            if (hoteles.getUsuario().getId_Usuario().equals(idUser)) {
                enviarHoteles.add(hoteles);
            }
        }

        return enviarHoteles;
    }

    @GetMapping("/hotelesDeLugar/{idLugar}")
    public List<Hoteles> hotelesDeLugar(@PathVariable Long idLugar) {
        List<Hoteles> hotelesList = hotelesService.findAll();
        List<Hoteles> hotelesDeLugar = new ArrayList<>();

        for (Hoteles hoteles : hotelesList) {
            if (hoteles.getLugares().getIdLugares().equals(idLugar)) {
                hotelesDeLugar.add(hoteles);
            }
        }
        return hotelesDeLugar;
    }

    @GetMapping("/hotelesPorHetiqueta/{idEtiqueta}")
    public List<Hoteles> hotelesPorEtiquetas(@PathVariable Long idEtiqueta) {
        List<Hoteles> hotelesList = hotelesService.findAll();
        List<Hoteles> hotelesPorEtiqueta = new ArrayList<>();

        for (Hoteles hoteles : hotelesList) {
            for (EtiquetasHoteles eh : hoteles.getEtiquetasHoteles())
                if (eh.getIdEtiquetaHoteles().equals(idEtiqueta)) {
                    hotelesPorEtiqueta.add(hoteles);
                }
        }
        return hotelesPorEtiqueta;
    }


    @PostMapping("/hoteles/{idUser}/{idLugar}")
    public Hoteles save(@RequestBody Hoteles hoteles, @PathVariable Long idUser, @PathVariable Long idLugar) {
        Usuario usuario = usuarioService.findById(idUser);
        Lugares lugar = lugaresService.findById(idLugar);
        hoteles.setUsuario(usuario);
        hoteles.setLugares(lugar);
        return hotelesService.save(hoteles);
    }

    @PutMapping("/hotel/{id}")
    public Hoteles update(@RequestBody Hoteles hoteles, @PathVariable Long id) {

        Hoteles auxHotel = hotelesService.findById(id);
        auxHotel.setDescripcion(hoteles.getDescripcion());
        auxHotel.setDireccion(hoteles.getDireccion());
        auxHotel.setNombre(hoteles.getNombre());
        auxHotel.setTelefono(hoteles.getTelefono());
        //auxUser.setCalificacion(hoteles.getCalificacion());
        auxHotel.setEtiquetasHoteles(hoteles.getEtiquetasHoteles());//lista de etiquetas
        return hotelesService.save(auxHotel);
    }

//    @PatchMapping("/hoteles/{id}")
//    public ResponseEntity<Hoteles> aprobar(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
//        Hoteles hotelesExistente = hotelesService.findById(id);
//
//        if (hotelesExistente == null) {
//            return ResponseEntity.notFound().build(); // Devuelve 404 si el lugar no existe
//        }
//
//        if (updates.containsKey("aprobado")) {
//            hotelesExistente.setAprobado((Boolean) updates.get("aprobado"));
//        }
//
//        return ResponseEntity.ok(hotelesService.save(hotelesExistente));
//    }

    @DeleteMapping("/hotel/{id}")
    public void delete(@PathVariable Long id) {
        hotelesService.delete(id);
    }


    //Reportes
    @GetMapping("/totalDeHoteles/{id}")
    public int cantidadDeHotelesCreados(@PathVariable Long id){
        return hotelesService.cantidadDeHotelesCreados(id);
    }

        @GetMapping("/cantidadDeHotelesCreadosParaAdmin")
    public int cantidadDeHotelesCreadosParaAdmin(){
        return hotelesService.cantidadDeHotelesCreadosParaAdmin();
    }
}
