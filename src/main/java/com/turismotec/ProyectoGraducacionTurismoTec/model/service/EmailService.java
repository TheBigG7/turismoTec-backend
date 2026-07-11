package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmailWithQRCode(String to, String subject, String body, Long idReserva, String tiporeserva) throws MailException, MessagingException, IOException, WriterException {

        // Generar código QR
        String qrContent = idReserva + ", " +tiporeserva;  // En este ejemplo, solo se usa el idReserva como contenido
        byte[] qrImageBytes = generateQRCode(qrContent, 200, 200);

        // Crear el mensaje
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setFrom("jorge.barreto97@gmail.com");

        // Usar CID para incrustar la imagen en el correo
        String cid = "qrCode";
        String emailBody = body + "<br><img src='cid:" + cid + "' width='200' height='200'/>";
        helper.setText(emailBody, true);
        helper.addInline(cid, new ByteArrayResource(qrImageBytes), "image/png");

        mailSender.send(message);
    }

    public void sendEmail(String to, String subject, String body) throws MailException, MessagingException {

        // Crear el mensaje
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setFrom("jorge.barreto97@gmail.com");

        mailSender.send(message);
    }

    private byte[] generateQRCode(String text, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0x000000 : 0xFFFFFF);
            }
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", outputStream);
        return outputStream.toByteArray();
    }
}
