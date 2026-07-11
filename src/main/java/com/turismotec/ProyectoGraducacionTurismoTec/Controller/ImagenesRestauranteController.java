package com.turismotec.ProyectoGraducacionTurismoTec.Controller;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ImagenesRestaurantes;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Restaurante;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.IRestauranteService;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.ImagesRestauranteService;
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
public class ImagenesRestauranteController {

    private final String uploadDir = "uploads/";

    @Autowired
    private ImagesRestauranteService imagenesRestaurantesService;

    @Autowired
    private IRestauranteService restaurantesService;

    @PostMapping("/imageRestaurantes/{idRestaurante}")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, @PathVariable Long idRestaurante) {
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

            Restaurante restaurante = restaurantesService.findById(idRestaurante);

            if (restaurante != null) {
                imagenesRestaurantesService.deleteImgById(restaurante.getIdRestaurante());
            }

            ImagenesRestaurantes imagenesRestaurante = new ImagenesRestaurantes();
            imagenesRestaurante.setUrl(fileUrl);
            imagenesRestaurante.setRestaurante(restaurante);

            imagenesRestaurantesService.save(imagenesRestaurante);

            return ResponseEntity.ok("Imagen subida exitosamente: " + fileUrl);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al subir la imagen: " + e);
        }
    }

    @GetMapping("/imageRestaurantes")
    public List<ImagenesRestaurantes> show() {
        return imagenesRestaurantesService.findAll();
    }

    @GetMapping("/imageRestaurantesById/{idRestaurante}")
    private List<ImagenesRestaurantes> showByIdRestaurante(@PathVariable Long idRestaurante) {
        List<ImagenesRestaurantes> imagenesRestauranteList = imagenesRestaurantesService.findAll();
        List<ImagenesRestaurantes> imagenesRestaurantes = new ArrayList<>();

        for (ImagenesRestaurantes imagenesRestaurantes1 : imagenesRestauranteList) {
            if (imagenesRestaurantes1.getRestaurante().getIdRestaurante().equals(idRestaurante)) {
                imagenesRestaurantes.add(imagenesRestaurantes1);
            }
        }
        return imagenesRestaurantes;
    }

    @GetMapping("/imageRestaurantes/file/{fileName}")
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
