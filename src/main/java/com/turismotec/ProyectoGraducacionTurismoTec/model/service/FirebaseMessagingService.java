package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;

@Service
public class FirebaseMessagingService {

    /**
     * Envía una notificación a un dispositivo específico identificado por su token.
     */
    public String sendNotification(String token, String title, String body) throws FirebaseMessagingException {
        System.out.println("Enviando notificación. Token: " + token + ", Título: " + title + ", Cuerpo: " + body);

        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build();

        Message message = Message.builder()
                .setToken(token)
                .setNotification(notification)
                .build();

        String response = FirebaseMessaging.getInstance().send(message);
        System.out.println("Respuesta de Firebase Messaging: " + response);
        return response;
    }
}
