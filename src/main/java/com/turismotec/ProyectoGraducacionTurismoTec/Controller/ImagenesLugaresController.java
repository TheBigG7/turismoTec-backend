package com.turismotec.ProyectoGraducacionTurismoTec.Controller;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ImagenesLugar;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Lugares;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.ILugaresService;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.ImagenesLugaresService;
import lombok.AllArgsConstructor;
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
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@CrossOrigin(origins = {"${frontend.url}"})
@RestController
@RequestMapping("/public/v1")
public class ImagenesLugaresController {

    private final String uploadDir = "uploads/";

    @Autowired
    private ImagenesLugaresService imagenesLugaresService;

    @Autowired
    private ILugaresService lugaresService;

    @PostMapping("/imageLugares/{idLugar}")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, @PathVariable Long idLugar) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("El archivo está vacío");
            }

            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            String fileName = file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir + fileName);

            Files.write(filePath, file.getBytes());

            String fileUrl = "/uploads/" + fileName;
            
            Lugares lugar = lugaresService.findById(idLugar);

            ImagenesLugar imagenesLugar = new ImagenesLugar();
            imagenesLugar.setUrl(fileUrl);
            imagenesLugar.setLugares(lugar);

            imagenesLugaresService.save(imagenesLugar);

            return ResponseEntity.ok("Imagen subida exitosamente: " + fileUrl);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al subir la imagen" +e);
        }
    }

    @GetMapping("/imageLugares")
    public List<ImagenesLugar> show(){
        return imagenesLugaresService.findAll();
    }

    @GetMapping("/imageLugares/{idLugar}")
    private List<ImagenesLugar> showByIdRestaurante(@PathVariable Long idLugar){
        List<ImagenesLugar> imagenesLugarList = imagenesLugaresService.findAll();
        List<ImagenesLugar> imagenesLugars = new ArrayList<>();

        for (ImagenesLugar imagenesLugars1: imagenesLugarList){
            if(imagenesLugars1.getLugares().getIdLugares().equals(idLugar)){
                imagenesLugars.add(imagenesLugars1);
            }
        }
        return imagenesLugars;
    }

    @GetMapping("/imageLugares/file/{fileName}")
    public ResponseEntity<?> getFile(@PathVariable String fileName) {
        try {
            // Ruta del archivo (ajústala según tu configuración)
            Path filePath = Paths.get("uploads").resolve(fileName).normalize();

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
