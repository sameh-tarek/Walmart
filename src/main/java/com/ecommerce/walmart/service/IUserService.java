package com.ecommerce.walmart.service;


import com.ecommerce.walmart.Entity.User;
import com.ecommerce.walmart.dto.userDto;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<userDto> getUsers();
    User registerUser(userDto request);
    userDto findByEmail(String email);

    void saveUserVerificationToken(User theUser, String verificationToken);

    String validateToken(String theToken);
}