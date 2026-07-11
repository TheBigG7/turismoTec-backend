package com.turismotec.ProyectoGraducacionTurismoTec.Controller;

import com.turismotec.ProyectoGraducacionTurismoTec.model.service.OpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = {"${frontend.url}"})
@RestController
@RequestMapping("/api/")
public class OpenAIController {

    @Autowired
    private OpenAIService openAIService;

    @PostMapping("query")
    public Map<String, Object> queryDatabase(@RequestBody Map<String, String> request) {
        try {
            String userQuery = request.get("query");

            // Llamar al servicio de OpenAI para procesar la consulta
            String result = openAIService.processUserQuery(userQuery);

            // Retornar la respuesta obtenida de OpenAI
            if (result.contains("No se encontraron coincidencias")) {
                return Map.of("success", false, "message", "No existen registros que coincidan con la consulta.");
            }

            return Map.of("success", true, "data", result);
        } catch (Exception e) {
            return Map.of("success", false, "error", e.getMessage());
        }
    }


}
