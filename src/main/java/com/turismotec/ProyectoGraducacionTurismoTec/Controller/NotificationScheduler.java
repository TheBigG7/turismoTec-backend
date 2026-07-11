package com.turismotec.ProyectoGraducacionTurismoTec.Controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ReservaMesa;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ReservasHabitaciones;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.FirebaseMessagingService;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.ReservaMesaServiceImplement;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.ReservasHabitacionesServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class NotificationScheduler {

    @Autowired
    private ReservaMesaServiceImplement reservaMesaService;

    @Autowired
    private ReservasHabitacionesServiceImplement reservasHabitacionesService;

    @Autowired
    private FirebaseMessagingService firebaseMessagingService;

    // Tiempo antes de la reserva para enviar el recordatorio (en minutos u horas según el caso)
    private static final int NOTIFICATION_BEFORE_MINUTES = 60;

    @Scheduled(fixedDelay = 60000) // Se ejecuta cada 60 segundos
    public void checkAndSendMesaNotifications() {
        List<ReservaMesa> reservasMesa = reservaMesaService.findAll();
        for (ReservaMesa reserva : reservasMesa) {
            // Si ya se envió la notificación, se omite esta reserva
            if (reserva.isNotificacionEnviada()) {
                continue;
            }

            Date fechaReserva = reserva.getFechaReserva();
            Calendar calReserva = Calendar.getInstance();
            calReserva.setTime(fechaReserva);
            calReserva.add(Calendar.DAY_OF_MONTH, 1); // Se suma un día

            String horaReservaStr = reserva.getHoraReserva(); // Formato "HH:mm"
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            Date horaReserva;
            try {
                horaReserva = sdf.parse(horaReservaStr);
            } catch (ParseException e) {
                e.printStackTrace();
                continue;
            }

            Calendar calHora = Calendar.getInstance();
            calHora.setTime(horaReserva);
            // Combinar la hora de la reserva con la fecha corregida
            calReserva.set(Calendar.HOUR_OF_DAY, calHora.get(Calendar.HOUR_OF_DAY));
            calReserva.set(Calendar.MINUTE, calHora.get(Calendar.MINUTE));
            calReserva.set(Calendar.SECOND, 0);
            calReserva.set(Calendar.MILLISECOND, 0);

            Calendar calNotification = (Calendar) calReserva.clone();
            calNotification.add(Calendar.MINUTE, -NOTIFICATION_BEFORE_MINUTES);

            Date now = new Date();
            System.out.println("Revisando ReservaMesa - ID: " + reserva.getIdReservaMesa() +
                    " | Ahora: " + now +
                    " | Notificación: " + calNotification.getTime() +
                    " | Reserva: " + calReserva.getTime());

            if (now.after(calNotification.getTime()) && now.before(calReserva.getTime())) {
                String token = reserva.getUsuario().getFirebaseToken();
                if (token == null || token.isEmpty()) {
                    System.out.println("No se encontró token para ReservaMesa ID: " + reserva.getIdReservaMesa());
                    continue;
                }
                String title = "Recordatorio de reserva de mesa";
                String body = "Tu reserva de mesa inicia en " + NOTIFICATION_BEFORE_MINUTES + " minutos.";
                try {
                    firebaseMessagingService.sendNotification(token, title, body);
                    System.out.println("Notificación de mesa enviada al token: " + token);
                    // Se marca la reserva como notificada para evitar envíos repetidos
                    reserva.setNotificacionEnviada(true);
                    reservaMesaService.save(reserva);
                } catch (FirebaseMessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Scheduled(fixedDelay = 60000) // Se ejecuta cada 60 segundos
    public void checkAndSendHabitacionNotifications() {
        List<ReservasHabitaciones> reservasHabitaciones = reservasHabitacionesService.findAll();
        for (ReservasHabitaciones reserva : reservasHabitaciones) {
            // Si ya se envió la notificación, se omite esta reserva
            if (reserva.isNotificacionEnviada()) {
                continue;
            }

            // Se obtiene la fecha de inicio de la reserva
            Date fechaInicio = reserva.getFechaIncioReserva();
            Calendar calReserva = Calendar.getInstance();
            calReserva.setTime(fechaInicio);
            // solo con el día
            calReserva.set(Calendar.HOUR_OF_DAY, 0);
            calReserva.set(Calendar.MINUTE, 0);
            calReserva.set(Calendar.SECOND, 0);
            calReserva.set(Calendar.MILLISECOND, 0);
            calReserva.add(Calendar.DAY_OF_MONTH, 1);

            // notificación fija: 18:00
            Calendar calNotification = (Calendar) calReserva.clone();
            calNotification.set(Calendar.HOUR_OF_DAY, 18);
            calNotification.set(Calendar.MINUTE, 0);
            calNotification.set(Calendar.SECOND, 0);
            calNotification.set(Calendar.MILLISECOND, 0);

            Calendar calReservaEnd = (Calendar) calReserva.clone();
            calReservaEnd.set(Calendar.HOUR_OF_DAY, 23);
            calReservaEnd.set(Calendar.MINUTE, 59);
            calReservaEnd.set(Calendar.SECOND, 59);
            calReservaEnd.set(Calendar.MILLISECOND, 999);

            Date now = new Date();
            System.out.println("Revisando ReservasHabitaciones - ID: " + reserva.getIdReservas() +
                    " | Ahora: " + now +
                    " | Notificación programada: " + calNotification.getTime() +
                    " | Fin de Reserva: " + calReservaEnd.getTime());

            if (now.after(calNotification.getTime()) && now.before(calReservaEnd.getTime())) {
                String token = reserva.getUsuario().getFirebaseToken();
                if (token == null || token.isEmpty()) {
                    System.out.println("No se encontró token para ReservasHabitaciones ID: " + reserva.getIdReservas());
                    continue;
                }
                String title = "Recordatorio de reserva de habitación";
                String body = "Tu reserva de habitación inicia pronto. ¡Prepárate!";
                try {
                    firebaseMessagingService.sendNotification(token, title, body);
                    System.out.println("Notificación de habitación enviada al token: " + token);
                    // Se marca la reserva como notificada para evitar envíos repetidos
                    reserva.setNotificacionEnviada(true);
                    reservasHabitacionesService.save(reserva);
                } catch (FirebaseMessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
