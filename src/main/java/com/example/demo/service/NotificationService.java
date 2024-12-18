package com.example.demo.service;

import com.example.demo.dto.NotificationDto;
import com.example.demo.entity.NotificationType;
import com.example.demo.request.NotificationRequest;
import com.example.demo.entity.Notification;
import com.example.demo.entity.NotificationStatus;
import com.example.demo.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationService {
    private final NotificationRepository repository;
    private final SmsService smsService;
    private final EmailService emailService;

    public NotificationService(NotificationRepository repository, SmsService smsService, EmailService emailService) {
        this.repository = repository;
        this.smsService = smsService;
        this.emailService = emailService;
    }

    public NotificationDto sendNotification(NotificationRequest request) {
        // Notification nesnesini oluştur
        Notification notification = new Notification();
        notification.setRecipient(request.getRecipient());
        notification.setMessage(request.getMessage());
        notification.setType(request.getType());
        notification.setStatus(NotificationStatus.PENDING);

        // Kaydet ve türüne göre işlem yap
        Notification savedNotification = repository.save(notification);
        if (notification.getType() == NotificationType.SMS) {
            smsService.sendSms(notification);
        } else if (notification.getType() == NotificationType.EMAIL) {
            emailService.sendEmail(notification);
        }

        // Gönderim bilgilerini güncelle
        savedNotification.setSentAt(LocalDateTime.now());
        savedNotification.setStatus(NotificationStatus.SENT);
        Notification updatedNotification = repository.save(savedNotification);

        // DTO'ya dönüştür ve döndür
        return toDto(updatedNotification);
    }

    private NotificationDto toDto(Notification notification) {
        NotificationDto dto = new NotificationDto();
        dto.setId(notification.getId());
        dto.setRecipient(notification.getRecipient());
        dto.setMessage(notification.getMessage());
        dto.setType(notification.getType());
        dto.setStatus(notification.getStatus());
        dto.setCreatedAt(notification.getCreatedAt());
        dto.setSentAt(notification.getSentAt());
        return dto;
    }
}
