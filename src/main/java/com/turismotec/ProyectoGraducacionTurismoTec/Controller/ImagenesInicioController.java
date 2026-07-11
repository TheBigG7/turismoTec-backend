package com.turismotec.ProyectoGraducacionTurismoTec.Controller;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ImagenesInicio;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.ImagenesInicioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@AllArgsConstructor
@CrossOrigin(origins = {"${frontend.url}"})
@RestController
@RequestMapping("/public/v1")
public class ImagenesInicioController {
    private final String uploadDir = "uploads/";

    @Autowired
    private ImagenesInicioService imagenesInicioService;


    @PostMapping("/imageInicio")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
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
            imagenesInicioService.save(fileUrl);

            return ResponseEntity.ok("Imagen subida exitosamente: " + fileUrl);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al subir la imagen" +e);
        }
    }

    @GetMapping("/imageInicio")
    public List<ImagenesInicio> show(){
        return imagenesInicioService.findAll();
    }

}

