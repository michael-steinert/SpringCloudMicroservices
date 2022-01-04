package com.company.notification;

import com.company.amqp.RabbitMQMessageProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(
        scanBasePackages = {
                "com.company.notification",
                "com.company.amqp"
        }
)
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

    /*
    @Bean
    CommandLineRunner commandLineRunner(RabbitMQMessageProducer rabbitMQMessageProducer, NotificationConfig notificationConfig) {
        return args -> {
            rabbitMQMessageProducer.publish(
                    "Test Message",
                    notificationConfig.getInternalExchange(),
                    notificationConfig.getInternalNotificationRoutingKey()
            );
        };
    }
    */
}