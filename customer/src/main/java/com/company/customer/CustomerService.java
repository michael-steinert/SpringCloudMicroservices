package com.company.customer;

import com.company.amqp.RabbitMQMessageProducer;
import com.company.clients.fraud.FraudCheckResponse;
import com.company.clients.fraud.IFraudClient;
import com.company.clients.notification.INotificationClient;
import com.company.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;
// import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(
        ICustomerRepository customerRepository,
        /* RestTemplate restTemplate, */
        IFraudClient fraudClient,
        INotificationClient notificationClient,
        RabbitMQMessageProducer rabbitMQMessageProducer
) {
    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        /* Using the Builder-Pattern */
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();
        customerRepository.saveAndFlush(customer);
        /* Better Approach with Open Feign */
        /*
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://FRAUD/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );
        */
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());
        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("Fraudster");
        }
        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hello %s, Welcome to the Microservice based System", customer.getFirstName())
        );

        /* Open Feign Approach */
        // notificationClient.sendNotification(notificationRequest);

        /* Asynchronous Message Queue approach */
        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );
    }
}
