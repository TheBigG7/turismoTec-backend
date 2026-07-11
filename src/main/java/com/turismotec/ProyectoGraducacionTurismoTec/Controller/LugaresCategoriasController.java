package com.turismotec.ProyectoGraducacionTurismoTec.Controller;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.EtiquetasLugares;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Lugares;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.LugaresEtiquetas;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Usuario;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.IEtiquetasLugaresService;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.ILugaresEtiquetasService;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.ILugaresService;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"${frontend.url}"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/public/v1")
public class LugaresCategoriasController {

    @Autowired
    private ILugaresEtiquetasService lugaresEtiquetasService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private ILugaresService lugaresService;

    @Autowired
    private IEtiquetasLugaresService etiquetasLugaresService;

    @GetMapping("/lugaresCategoriasFilter/{idCategoria}")
    public List<LugaresEtiquetas> lugaresCatagorias(@PathVariable Long idCategoria){
        List<LugaresEtiquetas> lugaresCategoriasList = lugaresEtiquetasService.findAll();
        List<LugaresEtiquetas> lugaresCategorias = new ArrayList<>();

        for (LugaresEtiquetas lugaresCategorias1: lugaresCategoriasList){
            if(lugaresCategorias1.getEtiquetas().getIdEtiqueta().equals(idCategoria) && lugaresCategorias1.getLugares().isAprobado() && lugaresCategorias1.getLugares().isVisualizacion()){
                lugaresCategorias.add(lugaresCategorias1);
            }
        }

        return lugaresCategorias;
    }

    @GetMapping("/categoriasLugaresFilter/{idLugar}")
    public List<LugaresEtiquetas> categoriasDeLugar(@PathVariable Long idLugar){
        List<LugaresEtiquetas> lugaresCategoriasList = lugaresEtiquetasService.findAll();
        List<LugaresEtiquetas> categoriasDeLugar = new ArrayList<>();

        for (LugaresEtiquetas lugaresCategorias1: lugaresCategoriasList){
            if(lugaresCategorias1.getLugares().getIdLugares().equals(idLugar)){
                categoriasDeLugar.add(lugaresCategorias1);
            }
        }
        return categoriasDeLugar;
    }

    @GetMapping("/lugaresEtiquetas")
    public List<LugaresEtiquetas> findAll(){
        return lugaresEtiquetasService.findAll();
    }

    @GetMapping("/lugaresEtiquetas/{id}")
    public LugaresEtiquetas findById(@PathVariable Long id){
        return lugaresEtiquetasService.findById(id);
    }

    @PostMapping("/lugaresEtiquetas/{idLugar}/{idEtiqueta}")
    public LugaresEtiquetas save(@RequestBody LugaresEtiquetas lugaresCategorias, @PathVariable Long idLugar, @PathVariable Long idEtiqueta){

        Lugares lugares = lugaresService.findById(idLugar);
        EtiquetasLugares etiquetasLugares = etiquetasLugaresService.findById(idEtiqueta);

        if (lugaresEtiquetasService.existsByLugarAndEtiqueta(idLugar, idEtiqueta)) {
            return null;
        }
        lugaresCategorias.setLugares(lugares);
        lugaresCategorias.setEtiquetas(etiquetasLugares);
        return  lugaresEtiquetasService.save(lugaresCategorias);

    }

    @DeleteMapping("/lugaresEtiquetas/{id}")
    public void delete(@PathVariable Long id){
        lugaresEtiquetasService.delete(id);
    }

    @GetMapping("/lugaresParaUsuario/{idUser}")
    public List<LugaresEtiquetas> lugaresRecomendados(@PathVariable Long idUser){
        List<LugaresEtiquetas> lugaresCategoriasList = lugaresEtiquetasService.findAll();
        Usuario usuario = usuarioService.findById(idUser);
        String[] preferencias = usuario.getPreferencias().split(",");
        List<LugaresEtiquetas> lugaresRecomendados = new ArrayList<>();

        for (LugaresEtiquetas lugares:lugaresCategoriasList){
            for(String preferenciasFor: preferencias){
                if (lugares.getEtiquetas().getEtiqueta().equals(preferenciasFor)){
                    lugaresRecomendados.add(lugares);
                }
            }
        }

        Set<Long> idsUnicos = new HashSet<>();
        List<LugaresEtiquetas> listaSinDuplicados = lugaresRecomendados.stream()
                .filter(l -> idsUnicos.add(l.getLugares().getIdLugares())) // Solo agrega si el ID no está en el Set
                .toList();


        return listaSinDuplicados;
    }
}
