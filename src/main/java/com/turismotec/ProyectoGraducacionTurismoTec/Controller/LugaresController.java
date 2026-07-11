package com.turismotec.ProyectoGraducacionTurismoTec.Controller;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Lugares;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Usuario;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.ILugaresService;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"${frontend.url}"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/public/v1")
public class LugaresController {

    @Autowired
    private ILugaresService lugaresService;

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/lugaresPublicos")
    public List<Lugares> obtenerLugaresAprobados() {
        return lugaresService.obtenerLugaresAprobadosAndVisualizacionTrue();
    }

    @GetMapping("/lugaresCreadoPorAdmin")
    public List<Lugares> obtenerLugaresCreadoPorAdmin() {
        return lugaresService.obtenerLugaresCreadoPorAdmin();
    }

    @GetMapping("/lugares")
    public List<Lugares> findAll(){
        return lugaresService.findAll();
    }

    @GetMapping("/lugares/{id}")
    public Lugares findById(@PathVariable Long id){
        return lugaresService.findById(id);
    }

    @GetMapping("/lugaresByIdUser/{idUser}")
    public List<Lugares> findByIdUser(@PathVariable Long idUser){
        List<Lugares> lugaresList = lugaresService.findAll();
        List<Lugares> enviarLugares = new ArrayList<>();

        for (Lugares lugares:lugaresList){
            if (lugares.getUsuario().getId_Usuario().equals(idUser)){
                enviarLugares.add(lugares);
            }
        }

        return enviarLugares;
    }

    @PostMapping("/lugares/{idUser}")
    public Lugares save(@RequestBody Lugares lugares, @PathVariable Long idUser){
        Usuario usuario = usuarioService.findById(idUser);
        lugares.setUsuario(usuario);
        return  lugaresService.save(lugares);
    }

    @PutMapping("/lugares/{id}")
    public Lugares update(@RequestBody Lugares lugares, @PathVariable Long id){

        Lugares auxUser =  lugaresService.findById(id);
        auxUser.setDescripcion(lugares.getDescripcion());
        auxUser.setDireccion(lugares.getDireccion());
        auxUser.setNombre(lugares.getNombre());
        auxUser.setAreaProtegida(lugares.isAreaProtegida());

        return lugaresService.save(auxUser);
    }

    @PatchMapping("/lugares/{id}")
    public ResponseEntity<Lugares> aprobar(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Lugares lugarExistente = lugaresService.findById(id);

        if (lugarExistente == null) {
            return ResponseEntity.notFound().build(); // Devuelve 404 si el lugar no existe
        }

        if (updates.containsKey("aprobado")) {
            lugarExistente.setAprobado((Boolean) updates.get("aprobado"));
        }

        return ResponseEntity.ok(lugaresService.save(lugarExistente));
    }


    @DeleteMapping("/lugares/{id}")
    public void delete(@PathVariable Long id){
        lugaresService.delete(id);
    }

}
