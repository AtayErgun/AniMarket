package com.example.demo.service;

import com.example.demo.entity.Notification;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    public void sendEmail(Notification notification) {
        // E-posta gönderim işlemini buraya ekle
        System.out.println("E-posta gönderiliyor: " + notification.getMessage());
    }
}
