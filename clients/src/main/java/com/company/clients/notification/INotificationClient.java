package com.company.clients.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "notification"
)
public interface INotificationClient {
    @PostMapping(path = "api/v1/notification")
    void sendNotification(NotificationRequest notificationRequest);
}
