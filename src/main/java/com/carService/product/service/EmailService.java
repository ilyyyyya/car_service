package com.carService.product.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;



@Service
@Slf4j

public class EmailService {


    @Autowired
    private JavaMailSender javaMailSender;


    public void sendEmail(String emailTo, String subject, String body) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom("ilya01122003lid@yandex.ru");
        simpleMailMessage.setTo(emailTo);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);


        javaMailSender.send(simpleMailMessage);
    }

}
