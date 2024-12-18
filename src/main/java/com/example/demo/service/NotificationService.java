package com.example.demo.service;

import com.example.demo.dto.NotificationDto;
import com.example.demo.entity.Notification;
import com.example.demo.entity.NotificationType;
import com.example.demo.repository.NotificationRepository;
import com.example.demo.request.NotificationRequest;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDateTime;

import static com.example.demo.entity.NotificationStatus.FAILED;
import static com.example.demo.entity.NotificationStatus.SENT;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository, EmailService emailService) {
        this.notificationRepository = notificationRepository;
        this.emailService = emailService;
    }

    public NotificationDto sendEmailNotification(NotificationRequest request) {
        Notification notification = new Notification();
        notification.setMessage(request.getMessage());
        notification.setRecipient(request.getRecipient());
        notification.setType(NotificationType.EMAIL);
        notification.setCreatedAt(LocalDateTime.now());

        try {
            // Gerçek e-posta gönderimi
            emailService.sendEmail(request.getRecipient(), "Bildirim Başlığı", request.getMessage());
            notification.setStatus(SENT);
            notification.setSentAt(LocalDateTime.now());
        } catch (Exception e) {
            notification.setStatus(FAILED);
        }

        return convertToDto(notificationRepository.save(notification));
    }

    public NotificationDto sendSmsNotification(NotificationRequest request) {
        Notification notification = new Notification();
        notification.setMessage(request.getMessage());
        notification.setRecipient(request.getRecipient());
        notification.setType(NotificationType.SMS);
        notification.setCreatedAt(LocalDateTime.now());

        try {
            // SMS gönderimi işlemi simüle ediliyor
            // Gerçek bir SMS gönderilmiyor, loglama ile simülasyon yapıyoruz
            System.out.println("SMS Sent to: " + request.getRecipient() + " with message: " + request.getMessage());

            // Gerçek SMS gönderilmediği için, simülasyon olarak başarılı kabul ediyoruz
            notification.setStatus(SENT);
            notification.setSentAt(LocalDateTime.now());
        } catch (Exception e) {
            notification.setStatus(FAILED);
        }

        return convertToDto(notificationRepository.save(notification));
    }
    public NotificationDto convertToDto(Notification notification) {
        return new NotificationDto(
                notification.getId(),
                notification.getMessage(),
                notification.getStatus(),
                notification.getType()
        );
    }

}
