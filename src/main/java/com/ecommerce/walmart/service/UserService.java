package com.ecommerce.walmart.service;


import com.ecommerce.walmart.Entity.User;
import com.ecommerce.walmart.Entity.VerificationToken;
import com.ecommerce.walmart.dto.userDto;

import java.util.List;

public interface UserService {
    List<userDto> getUsers();
    User registerUser(userDto request);
    userDto findByEmail(String email);

    void saveUserVerificationToken(User theUser, String verificationToken);

    String validateToken(String theToken);

    VerificationToken generateNewVerificationToken(String oldToken);
}