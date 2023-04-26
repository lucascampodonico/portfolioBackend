package com.porfolio.lucascampodonico.email;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@RequestMapping("/api/v1/")
@RestController
public class EmailController {

    @Autowired
    private JavaMailSender javaMailSender;

    @PostMapping("/send-email")
    public Map<String, String> sendEmail(@RequestBody EmailRequest emailRequest) throws AddressException, MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        String html = "<table border='1' style='border-collapse: collapse; width: 100%; height: 54px;'><tbody><tr style='height: 18px;'><td style='width: 100%; height: 18px;'>Nombre:&nbsp;"+emailRequest.getNombre()+"</td></tr><tr style='height: 18px;'><td style='width: 100%; background-color: #f2f2f2; height: 18px;'>Correo:&nbsp;"+emailRequest.getEmail()+"</td></tr><tr style='height: 18px;'><td style='width: 100%; height: 18px;'>Mensaje:&nbsp;"+emailRequest.getMensaje()+"</td></tr></tbody></table>";

        message.setSubject("Correo desde Portfolio");
        message.setContent(html, "text/html; charset=utf-8");
        
        // Establecer el destinatario y asunto del correo electrónico
        message.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse("lucasjoelcampodonico@gmail.com"));
        
        javaMailSender.send(message);
       // Crear y retornar la respuesta JSON como un mapa
       Map<String, String> response = new HashMap<>();
       response.put("msg", "Email sent successfully!");
       return response;
        
    }
}
