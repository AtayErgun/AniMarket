package com.example.demo.controller;

import com.example.demo.dto.NotificationDto;
import com.example.demo.entity.NotificationStatus;
import com.example.demo.entity.NotificationType;
import com.example.demo.request.NotificationRequest;
import com.example.demo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;


    @PostMapping("/email")
    public ResponseEntity<NotificationDto> sendEmail(@RequestBody NotificationRequest request) {
        try {
            NotificationDto response = notificationService.sendEmailNotification(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new NotificationDto(null, "Email gönderilemedi: " + e.getMessage(), NotificationStatus.FAILED, NotificationType.EMAIL));
        }
    }


    @PostMapping("/sms")
    public ResponseEntity<NotificationDto> sendSms(@RequestBody NotificationRequest request) {
        try {
            NotificationDto response = notificationService.sendSmsNotification(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new NotificationDto(null, "SMS gönderilemedi: " + e.getMessage(), NotificationStatus.FAILED, NotificationType.SMS));
        }
    }
}
