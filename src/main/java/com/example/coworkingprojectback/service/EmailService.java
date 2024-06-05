package com.example.coworkingprojectback.service;

import jakarta.mail.internet.MimeMessage;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import org.springframework.stereotype.Service;

import javax.imageio.IIOException;
import javax.mail.MessagingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private ResourceLoader resourceLoader;

    public void sendmail(String to, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        System.out.println("Enviando mail");
        mailSender.send(message);
    }
    public void sendHtmlEmail(String to, String subject, String body) throws IOException, MessagingException, IOException, jakarta.mail.MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom("");
        helper.setTo(to);
        helper.setSubject(subject);

        String htmlTemplate = readFile("classpath:templates/email/index.html");
        helper.setText(htmlTemplate, true);

        mailSender.send(message);
    }

    private String readFile(String path) throws IOException {
        Resource resource = (Resource) resourceLoader.getResource(path);
        try (InputStream inputStream = resource.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }
}

