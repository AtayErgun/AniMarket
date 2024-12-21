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

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/email")
    public ResponseEntity<NotificationDto> sendEmail(@RequestBody NotificationRequest request) {
        try {
            NotificationDto response = notificationService.sendEmailNotification(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            NotificationDto errorResponse = new NotificationDto();
            errorResponse.setMessage("Email gönderilemedi: " + e.getMessage());
            errorResponse.setStatus(NotificationStatus.FAILED);
            errorResponse.setType(NotificationType.EMAIL);
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @PostMapping("/sms")
    public ResponseEntity<NotificationDto> sendSms(@RequestBody NotificationRequest request) {
        try {
            NotificationDto response = notificationService.sendSmsNotification(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            NotificationDto errorResponse = new NotificationDto();
            errorResponse.setMessage("SMS gönderilemedi: " + e.getMessage());
            errorResponse.setStatus(NotificationStatus.FAILED);
            errorResponse.setType(NotificationType.SMS);
            return ResponseEntity.status(500).body(errorResponse);
        }
    }
}
