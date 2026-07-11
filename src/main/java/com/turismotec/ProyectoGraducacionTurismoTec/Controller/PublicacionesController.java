package com.turismotec.ProyectoGraducacionTurismoTec.Controller;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Publicaciones;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ReviewHotel;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Usuario;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.IPublicacionesService;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.IReviewHotelService;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.IUsuarioService;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@CrossOrigin(origins = {"${frontend.url}"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/public/v1")
public class PublicacionesController {

    private final String uploadDir = "uploads/ImagenesPublicaciones/";

    @Autowired
    private IPublicacionesService publicacionesService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private OpenAIService openAIService;

    @GetMapping("/publicaciones")
    public List<Publicaciones> findAll(){
        return publicacionesService.findAll();
    }

    @GetMapping("/publicaciones/{id}")
    public Publicaciones findById(@PathVariable Long id){
        return publicacionesService.findById(id);
    }

    @PostMapping("/publicaciones/{idUsuario}")
    public Publicaciones save(@RequestBody Publicaciones publicaciones, @PathVariable Long idUsuario){
        Usuario guardarUser = usuarioService.findById(idUsuario);
        publicaciones.setUsuario(guardarUser);
        String respueta = openAIService.evaluarComentarios(publicaciones.getTitulo()+ " " + publicaciones.getContenido());
        System.out.println("Respuesta"+respueta);

        if (respueta.equals("Falso.") || respueta.equals("falso.") || respueta.equals("Falso") || respueta.equals("falso")){
            return null;
        }else{
            return publicacionesService.save(publicaciones);
        }
    }

    @PutMapping("/publicaciones/{id}")
    public Publicaciones update(@RequestBody Publicaciones publicaciones, @PathVariable Long id){

        Publicaciones auxUser =  publicacionesService.findById(id);
        auxUser.setContenido(publicaciones.getContenido());
        auxUser.setTitulo(publicaciones.getTitulo());

        return publicacionesService.save(auxUser);
    }

    @DeleteMapping("/publicaciones/{id}")
    public void delete(@PathVariable Long id){
        publicacionesService.delete(id);
    }

    //Metodos para las images
    @PutMapping("/publicacionesImagen/{idPublicacion}")
    public Publicaciones insetImg(@RequestParam("file") MultipartFile file, @PathVariable Long idPublicacion){
        try{
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            Publicaciones publicaciones = publicacionesService.findById(idPublicacion);
            String fileName = file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir + fileName);
            Files.write(filePath, file.getBytes());

            String fileUrl = "/uploads/ImagenesPublicaciones/" + fileName;

            publicaciones.setUrlFotoForo(fileUrl);

            return  publicacionesService.save(publicaciones);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//Esto no se usar ojo
    @GetMapping("/imagePublicaciones/file/{fileName}")
    public ResponseEntity<?> getFile(@PathVariable String fileName) {
        try {
            Path filePath = Paths.get("uploads/ImagenesPublicaciones/").resolve(fileName).normalize();

            // Validar si el archivo existe
            if (!Files.exists(filePath)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El archivo no existe.");
            }
            // Obtener el contenido del archivo
            byte[] fileContent = Files.readAllBytes(filePath);
            // Determinar el tipo MIME del archivo
            String mimeType = Files.probeContentType(filePath);

            // Construir la respuesta
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(mimeType != null ? mimeType : "application/octet-stream"))
                    .header("Content-Disposition", "attachment; filename=\"" + fileName + "\"")
                    .body(fileContent);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener el archivo: " + e.getMessage());
        }
    }
}
