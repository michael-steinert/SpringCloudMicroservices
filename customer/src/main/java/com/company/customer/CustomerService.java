package com.company.customer;

import org.springframework.stereotype.Service;

@Service
public record CustomerService(ICustomerRepository customerRepository) {
    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        /* Using the Builder-Pattern */
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();
        customerRepository.save(customer);
    }
}
