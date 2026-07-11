package com.turismotec.ProyectoGraducacionTurismoTec.Controller;

import com.google.zxing.WriterException;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Usuario;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.EmailService;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.IUsuarioService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = {"${frontend.url}"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/public/v1")
public class EmalController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private IUsuarioService usuarioService;
    // Endpoint para enviar el correo
    // Endpoint para enviar el correo con el código QR de la reserva
    @PostMapping("/send-email/{idUsuario}/{idReserva}/{tiporeserva}")
    public String sendEmail(@PathVariable Long idUsuario, @PathVariable Long idReserva,@PathVariable String tiporeserva) throws MessagingException, IOException, WriterException {

        Usuario usuario = usuarioService.findById(idUsuario);

        String to = usuario.getCorreo();
        String subject = "Confirmación de la reservación";
        String body = "<html>" +
                "<body>" +
                "<h2>Hola " + usuario.getNombre() + "!</h2>" +
                "<p>Este correo es para <strong>confirmar tu reservación</strong>.</p>" +
                "<br>" +
                "<p><strong>Muestra el Código QR al encargado para que te entreguen tu lugar reservado</strong></p>" +
                "<br>" +
                "<p>Esperamos que tengas un excelente día y que disfrutes de tu estancia.</p>" +
                "<p><em>¡Nos vemos pronto!</em></p>" +
                "<p>Saludos,<br><strong>El equipo de reservas</strong></p>" +
                "</body>" +
                "</html>";

        emailService.sendEmailWithQRCode(to, subject, body, idReserva,tiporeserva);
        return "Correo enviado con éxito a " + to;
    }

    @PostMapping("/send-email-aprobado/{idUsuario}")
    public String sendEmailAprobado(@PathVariable Long idUsuario) throws MessagingException{

        Usuario usuario = usuarioService.findById(idUsuario);

        String to = usuario.getCorreo();
        String subject = "Correo de aprobacion";
        String body = "<html>" +
                "<body>" +
                "<h2>Hola " + usuario.getNombre() + "!</h2>" +
                "<p>Este correo es para notificarte que se ha aprobado su lugar.</p>" +
                "<p>Gracias por compartir con nuestra comunidad!.</p>" +
                "<p>Saludos,<br><strong>El equipo de administrativo</strong></p>" +
                "</body>" +
                "</html>";
        emailService.sendEmail(to, subject, body);

        return "Se ha enviado el corro de aprobado";
    }
}
