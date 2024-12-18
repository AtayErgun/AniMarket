package com.example.demo.service;

import com.example.demo.entity.Notification;
import org.springframework.stereotype.Service;

@Service
public class SmsService {
    public void sendSms(Notification notification) {
        // SMS gönderim işlemini buraya ekle
        System.out.println("SMS gönderiliyor: " + notification.getMessage());
    }
}
