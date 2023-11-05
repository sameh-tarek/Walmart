package com.ecommerce.walmart.service;

import com.ecommerce.walmart.model.auth.AuthenticationRequest;
import com.ecommerce.walmart.model.auth.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
