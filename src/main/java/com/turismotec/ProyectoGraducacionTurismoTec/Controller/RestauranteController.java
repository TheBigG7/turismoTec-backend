package com.turismotec.ProyectoGraducacionTurismoTec.Controller;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.*;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.ILugaresService;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.IRestauranteService;
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
public class RestauranteController {

    @Autowired
    private IRestauranteService restauranteService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private ILugaresService lugaresService;

    @GetMapping("/restaurante")
    public List<Restaurante> findAll(){
        return restauranteService.findAll();
    }

    @GetMapping("/restaurante/{id}")
    public Restaurante findById(@PathVariable Long id){
        return restauranteService.findById(id);
    }

    @GetMapping("/restaurantesByIdUser/{idUser}")
    public List<Restaurante> findByIdUser(@PathVariable Long idUser){
        List<Restaurante> restaurantesList = restauranteService.findAll();
        List<Restaurante> enviarRestaurantes = new ArrayList<>();

        for (Restaurante restaurante:restaurantesList){
            if (restaurante.getUsuario().getId_Usuario().equals(idUser)){
                enviarRestaurantes.add(restaurante);
            }
        }

        return enviarRestaurantes;
    }

    @GetMapping("/restaurantesPorHetiqueta/{idEtiqueta}")
    public List<Restaurante> restaurantePorEtiquetas(@PathVariable Long idEtiqueta) {
        List<Restaurante> restaurantesList = restauranteService.findAll();
        List<Restaurante> restaurantesPorEtiqueta = new ArrayList<>();

        for (Restaurante restaurantes : restaurantesList) {
            for (EtiquetasRestaurantes eh : restaurantes.getEtiquetasRestaurantes())
                if (eh.getIdEtiquetaRestaurante().equals(idEtiqueta)) {
                    restaurantesPorEtiqueta.add(restaurantes);
                }
        }
        return restaurantesPorEtiqueta;
    }

    @GetMapping("/restauranteDeLugar/{idLugar}")
    public List<Restaurante> restaurantesDeLugar(@PathVariable Long idLugar){
        List<Restaurante> restauranteList = restauranteService.findAll();
        List<Restaurante> restauranteDeLugar = new ArrayList<>();

        for (Restaurante restaurante: restauranteList){
            if(restaurante.getLugares().getIdLugares().equals(idLugar)){
                restauranteDeLugar.add(restaurante);
            }
        }
        return restauranteDeLugar;
    }

    @PostMapping("/restaurante/{idUser}/{idLugar}")
    public Restaurante save(@RequestBody Restaurante restaurante, @PathVariable Long idUser, @PathVariable Long idLugar){
        Usuario usuario = usuarioService.findById(idUser);
        Lugares lugar =  lugaresService.findById(idLugar);
        restaurante.setUsuario(usuario);
        restaurante.setLugares(lugar);
        return restauranteService.save(restaurante);
    }

    @PutMapping("/restaurante/{id}")
    public Restaurante update(@RequestBody Restaurante restaurante, @PathVariable Long id) {

        Restaurante auxUser = restauranteService.findById(id);
        auxUser.setDescripcion(restaurante.getDescripcion());
        auxUser.setNombre(restaurante.getNombre());
        auxUser.setTelefono(restaurante.getTelefono());
        auxUser.setMenu(restaurante.getMenu());
        auxUser.setDireccion(restaurante.getDireccion());
        auxUser.setEtiquetasRestaurantes(restaurante.getEtiquetasRestaurantes());// lista de etiquetas
        return restauranteService.save(auxUser);
    }

    @DeleteMapping("/restaurante/{id}")
    public void delete(@PathVariable Long id){
        restauranteService.delete(id);
    }


    //Reporte
    @GetMapping("/cantidadDeRestaurantesCreados/{id}")
    public int cantidadDeRestaurantesCreados(@PathVariable Long id){
        return restauranteService.cantidadDeRestaurantesCreados(id);
    }

    @GetMapping("/cantidadDeRestaurantesCreadosParaAdmin")
    public int cantidadDeRestaurantesCreadosParaAdmin(){
        return restauranteService.cantidadDeRestaurantesCreadosParaAdmin();
    }
}
