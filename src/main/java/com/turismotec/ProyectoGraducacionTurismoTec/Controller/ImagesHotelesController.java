package com.turismotec.ProyectoGraducacionTurismoTec.Controller;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ImagenesHoteles;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Hoteles;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.IHotelesService;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.ImagenesHotelService;
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
public class ImagesHotelesController {

    private final String uploadDir = "uploads/";

    @Autowired
    private ImagenesHotelService imagenesHotelesService;

    @Autowired
    private IHotelesService hotelesService;

    @PostMapping("/imageHoteles/{idHotel}")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, @PathVariable Long idHotel) {
        try {
            System.out.println("Archivo: "+file);
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

            Hoteles hotel = hotelesService.findById(idHotel);

//            if (hotel != null) {
//                System.out.println("Entro a eliminar las images");
//                imagenesHotelesService.deleteImgById(idHotel);
//            }


            List<ImagenesHoteles> imagenesActuales = imagenesHotelesService.findByHotelId(idHotel);

            if (hotel != null && !imagenesActuales.isEmpty()) {
                System.out.println("Eliminando imágenes existentes para el hotel " + idHotel);
                imagenesHotelesService.deleteImgById(idHotel);
            }

            ImagenesHoteles imagenesHotel = new ImagenesHoteles();
            imagenesHotel.setUrl(fileUrl);
            System.out.println("Url: " + fileUrl);
            imagenesHotel.setHoteles(hotel);

            imagenesHotelesService.save(imagenesHotel);

            return ResponseEntity.ok("Imagen subida exitosamente: " + fileUrl);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al subir la imagen: " + e);
        }
    }

    @GetMapping("/imageHoteles")
    public List<ImagenesHoteles> show() {
        return imagenesHotelesService.findAll();
    }

    @GetMapping("/imageHoteles/{idHotel}")
    private List<ImagenesHoteles> showByIdHotel(@PathVariable Long idHotel) {
        List<ImagenesHoteles> imagenesHotelList = imagenesHotelesService.findAll();
        List<ImagenesHoteles> imagenesHotels = new ArrayList<>();

        for (ImagenesHoteles imagenesHotels1 : imagenesHotelList) {
            if (imagenesHotels1.getHoteles().getIdHotel().equals(idHotel)) {
                imagenesHotels.add(imagenesHotels1);
            }
        }
        return imagenesHotels;
    }

    @GetMapping("/imageHoteles/file/{fileName}")
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
