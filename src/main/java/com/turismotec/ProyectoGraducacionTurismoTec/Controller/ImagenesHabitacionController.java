package com.turismotec.ProyectoGraducacionTurismoTec.Controller;


import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Habitaciones;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ImagenesHabitaciones;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.IHabitacionesService;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.ImagenesHabitacionService;
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
public class ImagenesHabitacionController {

    private final String uploadDir = "uploads/";

    @Autowired
    private ImagenesHabitacionService imagenesHabitacionService;

    @Autowired
    private IHabitacionesService habitacionService;

    @PostMapping("/imageHabitacion/{idHabitacion}")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, @PathVariable Long idHabitacion) {
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

            Habitaciones habitacion = habitacionService.findById(idHabitacion);

            if (habitacion != null) {
                imagenesHabitacionService.deleteImgById(idHabitacion);
            }

            ImagenesHabitaciones imagenesHabitacion = new ImagenesHabitaciones();
            imagenesHabitacion.setUrl(fileUrl);
            imagenesHabitacion.setHabitacion(habitacion);

            imagenesHabitacionService.save(imagenesHabitacion);

            return ResponseEntity.ok("Imagen subida exitosamente: " + fileUrl);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al subir la imagen: " + e);
        }
    }

    @GetMapping("/imageHabitacion")
    public List<ImagenesHabitaciones> show() {
        return imagenesHabitacionService.findAll();
    }

    @GetMapping("/imageHabitacion/{idHabitacion}")
    private List<ImagenesHabitaciones> showByIdHotel(@PathVariable Long idHabitacion) {
        List<ImagenesHabitaciones> imagenesHabitacionList = imagenesHabitacionService.findAll();
        List<ImagenesHabitaciones> imagenesHabitacion = new ArrayList<>();

        for (ImagenesHabitaciones imagenesHabitacion1 : imagenesHabitacionList) {
            System.out.println("Enviando ");
            if (imagenesHabitacion1.getHabitacion().getIdHabitacion().equals(idHabitacion)) {
                imagenesHabitacion.add(imagenesHabitacion1);
            }
        }
        return imagenesHabitacion;
    }

    @GetMapping("/imageHabitacion/file/{fileName}")
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
