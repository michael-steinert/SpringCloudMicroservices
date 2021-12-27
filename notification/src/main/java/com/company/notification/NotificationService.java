package com.company.notification;

import com.company.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record NotificationService(INotificationRepository notificationRepository) {
    public void sendNotification(NotificationRequest notificationRequest) {
        notificationRepository.save(
                /* Using the Builder-Pattern */
                Notification.builder()
                        .toCustomerId(notificationRequest.toCustomerId())
                        .toCustomerEmail(notificationRequest.toCustomerName())
                        .sender("Michael")
                        .message(notificationRequest.message())
                        .sentAt(LocalDateTime.now())
                        .build()
        );
    }
}
