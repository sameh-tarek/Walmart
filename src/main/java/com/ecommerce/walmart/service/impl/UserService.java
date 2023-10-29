package com.ecommerce.walmart.service.impl;

import com.ecommerce.walmart.Entity.User;
import com.ecommerce.walmart.Entity.VerificationToken;
import com.ecommerce.walmart.dto.userDto;
import com.ecommerce.walmart.exception.AppException;
import com.ecommerce.walmart.exception.UserAlreadyExistsException;
import com.ecommerce.walmart.mapper.UserMapper;
import com.ecommerce.walmart.repository.UserRepository;
import com.ecommerce.walmart.repository.VerificationTokenRepository;
import com.ecommerce.walmart.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository tokenRepository;
    private final UserMapper userMapper;

    @Override
    public List<userDto> getUsers() {
        return userMapper.toUserDtos(userRepository.findAll());
    }

    @Override
    public User registerUser(userDto request) {
        boolean userExist = userRepository.existsByEmail(request.getEmail());
        if (userExist) {
            throw new UserAlreadyExistsException(
                    "User with email "+request.getEmail() + " already exists");
        }
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        var newUser = userMapper.toUser(request);
        return userRepository.save(newUser);
    }

    @Override
    public userDto findByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new AppException("User not found", HttpStatus.NOT_FOUND)
        );
        return userMapper.toUserDto(user);
    }

    @Override
    public void saveUserVerificationToken(User theUser, String token) {
        var verificationToken = new VerificationToken(token, theUser);
        tokenRepository.save(verificationToken);
    }

    @Override
    public String validateToken(String theToken) {
        VerificationToken token = tokenRepository.findByToken(theToken);
        if(token == null){
            return "Invalid verification token";
        }
        User user = token.getUser();
        Calendar calendar = Calendar.getInstance();
        if ((token.getExpirationTime().getTime()-calendar.getTime().getTime())<= 0){
            return "Verification link already expired," +
                    " Please, click the link below to receive a new verification link";
        }
        user.setEnabled(true);
        userRepository.save(user);
        return "valid";
    }

    @Override
    public VerificationToken generateNewVerificationToken(String oldToken) {
        VerificationToken verificationToken = tokenRepository.findByToken(oldToken);
        var tokenExpirationTime = new VerificationToken();
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationToken.setExpirationTime(tokenExpirationTime.getTokenExpirationTime());
        return tokenRepository.save(verificationToken);
    }
}