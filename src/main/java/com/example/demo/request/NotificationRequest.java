package com.example.demo.request;

import com.example.demo.entity.NotificationType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public class NotificationRequest {
    @NotNull(message = "Recipient boş olamaz")
    @NotEmpty(message = "Recipient boş olamaz")
    private String recipient;

    @NotNull(message = "Message boş olamaz")
    @NotEmpty(message = "Message boş olamaz")
    private String message;
    private NotificationType type;

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }
}
