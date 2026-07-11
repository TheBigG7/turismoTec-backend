package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatFunction;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class  OpenAIService {

    @Value("${openai.api.key}")
    private String apiKey;

    @Autowired
    private DatabaseService databaseService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String processUserQuery(String userQuery) {
        OpenAiService openAiService = new OpenAiService(apiKey);

        // Definición de las funciones disponibles en OpenAI
        ChatFunction obtenerLugaresFunction = ChatFunction.builder()
                .name("recomendarLugar")
                .description("Devuelve un lugar turístico recomendado")
                .build();

        ChatFunction obtenerHotelesFunction = ChatFunction.builder()
                .name("recomendarHotel")
                .description("Devuelve un hotel recomendado ")
                .build();

        ChatFunction obtenerRestaurantesFunction = ChatFunction.builder()
                .name("recomendarRestaurante")
                .description("Devuelve un restaurante recomendado")
                .build();

        ChatFunction saludarFunction = ChatFunction.builder()
                .name("saludar")
                .description("Saludar")
                .build();


        ChatMessage userMessage = new ChatMessage("user", userQuery);

        // Crear la solicitud con funciones disponibles
        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(Collections.singletonList(userMessage))
                .functions(Arrays.asList(obtenerLugaresFunction, obtenerHotelesFunction, obtenerRestaurantesFunction,saludarFunction))
                .maxTokens(500)
                .build();

        // Obtener la respuesta de OpenAI
        ChatCompletionResult result = openAiService.createChatCompletion(request);
        ChatMessage responseMessage = result.getChoices().get(0).getMessage();

        if (responseMessage.getFunctionCall() == null) {
            return "Por favor hazme preguntas sobre restaurantes, hoteles o lugares";
        }
        // Determinar qué función ejecutar
        String functionToCall = responseMessage.getFunctionCall().getName();


        switch (functionToCall) {
            case "recomendarLugar":
                ChatMessage systemMessageSend = new ChatMessage("system",
                        "Convierteme esto para que pueda entender el ususarioy resumido en menos de 100 palabras: " + recomendarLugar());
                ChatCompletionRequest requestSend = ChatCompletionRequest.builder()
                        .model("gpt-3.5-turbo")
                        .messages(Collections.singletonList(systemMessageSend))
                        .maxTokens(100)
                        .build();
                ChatCompletionResult resultSend = openAiService.createChatCompletion(requestSend);
                return resultSend.getChoices().get(0).getMessage().getContent();

            case "recomendarHotel":
                ChatMessage hotelSend = new ChatMessage("system",
                        "Convierteme esto para que pueda entender el ususarioy resumido en menos de 100 palabras: " + recomendarHotel());
                ChatCompletionRequest requesthotelSend = ChatCompletionRequest.builder()
                        .model("gpt-3.5-turbo")
                        .messages(Collections.singletonList(hotelSend))
                        .maxTokens(100)
                        .build();
                ChatCompletionResult resultHotelSend = openAiService.createChatCompletion(requesthotelSend);
                return resultHotelSend.getChoices().get(0).getMessage().getContent();
            case "recomendarRestaurante":

                ChatMessage restauranteSend = new ChatMessage("system",
                        "Convierteme esto para que pueda entender el ususarioy resumido en menos de 100 palabras: " + recomendarRestaurante());
                ChatCompletionRequest requesRestauranteSend = ChatCompletionRequest.builder()
                        .model("gpt-3.5-turbo")
                        .messages(Collections.singletonList(restauranteSend))
                        .maxTokens(100)
                        .build();
                ChatCompletionResult resultRestauranteSend = openAiService.createChatCompletion(requesRestauranteSend);
                return resultRestauranteSend.getChoices().get(0).getMessage().getContent();

            case "saludar":
                ChatMessage saludarSend = new ChatMessage("system",
                        saludar() );
                ChatCompletionRequest requesSaludarSend = ChatCompletionRequest.builder()
                        .model("gpt-3.5-turbo")
                        .messages(Collections.singletonList(saludarSend))
                        .maxTokens(100)
                        .build();
                ChatCompletionResult resulsaluadrSend = openAiService.createChatCompletion(requesSaludarSend);
                return resulsaluadrSend.getChoices().get(0).getMessage().getContent();
            default:
                return "Por favor hazme preguntas sobre restaurantes, hoteles o lugares";
        }
    }

    public String recomendarLugar() {
        return executeQuery("SELECT nombre, descripcion, direccion FROM lugares ORDER BY RAND() LIMIT 1;");
    }

    public String recomendarHotel() {
        return executeQuery("SELECT nombre, descripcion, direccion FROM hoteles ORDER BY RAND() LIMIT 1;");
    }

    public String recomendarRestaurante() {
        return executeQuery("SELECT nombre, descripcion, direccion FROM restaurante ORDER BY RAND() LIMIT 1;");
    }

    public String saludar(){
        return "Saluda de forma amable";
    }

    private String executeQuery(String sqlQuery) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Map<String, Object>> queryResult = jdbcTemplate.queryForList(sqlQuery);
            if (queryResult.isEmpty()) {
                response.put("success", false);
                response.put("message", "No se encontraron resultados en la base de datos.");
            } else {
                response.put("success", true);
                response.put("data", queryResult);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", "Lo sentimos, hemos tenido un problema.");
        }
        return response.toString();
    }

    public String generarRecomendaciones(String query){
        String etiquetas = executeQuery("SELECT etiqueta FROM etiquetas_lugares");

        OpenAiService openAiService = new OpenAiService(apiKey);

        ChatMessage systemMessage = new ChatMessage("system", "Lee estas etiquetas " + etiquetas + " y responde en base a esto");
        ChatMessage userMessage = new ChatMessage("user", query + "Con esta información y solo con palabras sueltas que tipo de lugares me recomiendas visitar. Separa cada actividad con una coma y sin espacios");
        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(Arrays.asList(systemMessage, userMessage))
                .maxTokens(500)
                .build();
        ChatCompletionResult result = openAiService.createChatCompletion(request);
        ChatMessage responseMessage = result.getChoices().get(0).getMessage();
        return responseMessage.getContent();
    }

    public String evaluarComentarios(String query){
        OpenAiService openAiService = new OpenAiService(apiKey);
        ChatMessage systemMessage = new ChatMessage("system", "Necesito que actues como una persona moral y evalues si un escrito tiene malas palabras, en caso de que las tenga una mala palabra solo manda: falso .Asi tal cual");
        ChatMessage userMessage = new ChatMessage("user", query);

        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(Arrays.asList(systemMessage, userMessage))
                .maxTokens(500)
                .build();
        ChatCompletionResult result = openAiService.createChatCompletion(request);
        ChatMessage responseMessage = result.getChoices().get(0).getMessage();
        return responseMessage.getContent();
    }
}


