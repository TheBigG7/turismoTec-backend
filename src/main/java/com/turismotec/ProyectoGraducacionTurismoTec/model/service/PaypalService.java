package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
public class PaypalService {
    @Value("${paypal.client.id}")
    private String clientId;

    @Value("${paypal.client.secret}")
    private String clientSecret;

    @Value("${paypal.mode}")
    private String mode;

    // URLs de la API de PayPal
    private static final String PAYPAL_API = "https://api-m.sandbox.paypal.com/v2/checkout/orders";
    private static final String TOKEN_URL = "https://api-m.sandbox.paypal.com/v1/oauth2/token";

    // Cliente HTTP para realizar las peticiones
    private final RestTemplate restTemplate = new RestTemplate();

    public String createOrder(double amount, String currency) {

        // Obtener el token de acceso de PayPal
        String accessToken = getAccessToken();

        // Configurar los encabezados de la solicitud
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken); // Token de autenticación
        headers.setContentType(MediaType.APPLICATION_JSON); // Formato de la solicitud

        // Construir el cuerpo JSON de la orden de pago
        JSONObject orderJson = new JSONObject();
        orderJson.put("intent", "CAPTURE"); // Capturar el pago automáticamente

        JSONObject purchaseUnit = new JSONObject();
        purchaseUnit.put("amount", new JSONObject()
                .put("currency_code", currency) // Moneda
                .put("value", amount)); // Monto a pagar

        // Agregar los datos de la compra a la orden
        orderJson.put("purchase_units", Collections.singletonList(purchaseUnit));

        // Crear la solicitud HTTP con el cuerpo JSON y los encabezados
        HttpEntity<String> request = new HttpEntity<>(orderJson.toString(), headers);

        // Enviar la solicitud a PayPal y recibir la respuesta
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(PAYPAL_API, request, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody(); // Devuelve la respuesta de PayPal si es exitosa
            } else {
                return "Error al crear la orden: " + response.getStatusCode();
            }
        } catch (Exception e) {
            return "Error al comunicarse con PayPal: " + e.getMessage();
        }
    }

    private String getAccessToken() {

        // Configurar los encabezados para la autenticación básica (client_id y client_secret)
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(clientId, clientSecret); // Autenticación en Base64
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED); // Formato del cuerpo

        // Cuerpo de la solicitud para obtener el token
        HttpEntity<String> request = new HttpEntity<>("grant_type=client_credentials", headers);

        // Enviar la solicitud a PayPal y recibir la respuesta con el token
        ResponseEntity<Map> response = restTemplate.postForEntity(TOKEN_URL, request, Map.class);

        // Extraer el token de la respuesta JSON y devolverlo
        return (String) response.getBody().get("access_token");
    }
}
