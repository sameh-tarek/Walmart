package com.ecommerce.walmart.model.registration;

import com.ecommerce.walmart.Entity.enums.Role;

public record RegistrationRequest(
        String firstName,
        String lastName,
        String email,
        String password,
        Role role) {
}