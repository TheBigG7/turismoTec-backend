package com.turismotec.ProyectoGraducacionTurismoTec.Controller;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Usuario;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.IUsuarioService;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.OpenAIService;
import lombok.extern.java.Log;
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
import java.util.Map;

@CrossOrigin(origins = {"${frontend.url}"})
@RestController
@RequestMapping("api/v1")
public class UsuarioController {

    @Autowired
    private OpenAIService openAIService;

    private final String uploadDir = "uploads/";

    @Autowired
    private IUsuarioService userService;

    @GetMapping("/usuario")
    public List<Usuario> findAll(){
        return userService.findAll();
    }

    @GetMapping("/usuario/{id}")
    public Usuario findById(@PathVariable Long id){
        return userService.findById(id);
    }

    @GetMapping("/usuarioDeAsociado/{id}")
    public List<Usuario> findByidpersonaqueloCreo(@PathVariable Long id){
        return userService.findByidpersonaqueloCreo(id);
    }

    @PostMapping("/usuario")
    public Usuario save(@RequestBody Usuario user){
        return  userService.save(user);
    }

    @PutMapping("/usuarioImagen/{idUser}")
    public Usuario insetImg(@RequestParam("file") MultipartFile file, @PathVariable Long idUser){
        try{
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            Usuario usuario = userService.findById(idUser);
            String fileName = file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir + fileName);
            Files.write(filePath, file.getBytes());

            String fileUrl = "/uploads/" + fileName;

            usuario.setUrlImageUser(fileUrl);

            return  userService.save(usuario);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/usuario/{id}")
    public Usuario update(@RequestBody Usuario user, @PathVariable Long id){

        Usuario existingUser = userService.findById(id);

        existingUser.setApellido(user.getApellido());
        existingUser.setNombre(user.getNombre());
        existingUser.setFechaNacimiento(user.getFechaNacimiento());
        existingUser.setIdioma(user.getIdioma());
        existingUser.setPaisOrigen(user.getPaisOrigen());
        existingUser.setCorreo(user.getCorreo());
        return userService.save(existingUser);
    }

    @DeleteMapping("/usuario/{id}")
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }

    //Metodos para las images
    @GetMapping("/imagePerfilUsuario/file/{fileName}")
    public ResponseEntity<?> getFile(@PathVariable String fileName) {
        try {
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

    //REPORTES//
    @GetMapping("cantidadPersonasRegistradasByMesYYear/{year}")
    public List<String> CantidadPersonasRegistradasByMesYYear(@PathVariable int year){
        return userService.CantidadPersonasRegistradasByMesYYear(year);
    }

    @GetMapping("totalUsuarioRegistrados")
    public Long totalUsuarioRegistrados(){
        return  userService.totalUsuarioRegistrados();
    }

    @GetMapping("usuarioPorPaisOrigen")
    public List<String> usuarioPorPaisOrigen(){
        return  userService.usuarioPorPaisOrigen();
    }

    //Solicitudad a openia
    @PostMapping("preferenciasUser/{id}")
    private String obtenerPreferencias(@RequestBody String request, @PathVariable Long id){
        Usuario existingUser = userService.findById(id);
        System.out.println("Preferencias "+openAIService.generarRecomendaciones(request));
        existingUser.setPreferencias(openAIService.generarRecomendaciones(request));
        userService.save(existingUser);
        return "completado";
    }

    // firebase
    @PutMapping("/usuario/token/{id}")
    public ResponseEntity<Usuario> updateFirebaseToken(
            @PathVariable Long id,
            @RequestBody Map<String, String> payload) {

        Usuario usuario = userService.findById(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        // Extraer el token del payload JSON
        String firebaseToken = payload.get("firebaseToken");
        if (firebaseToken == null || firebaseToken.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // Actualizar el campo y guardar el usuario
        usuario.setFirebaseToken(firebaseToken);
        Usuario updatedUser = userService.save(usuario);

        return ResponseEntity.ok(updatedUser);
    }

}
