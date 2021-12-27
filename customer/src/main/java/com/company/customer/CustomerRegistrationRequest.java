package com.company.customer;

/* Using a Record instead of a Class gives Immutability to Strings and Equals for free */
public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email
) {
}
