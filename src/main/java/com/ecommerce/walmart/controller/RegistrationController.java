package com.ecommerce.walmart.controller;

import com.ecommerce.walmart.Entity.User;
import com.ecommerce.walmart.Entity.VerificationToken;
import com.ecommerce.walmart.dto.userDto;
import com.ecommerce.walmart.event.RegistrationCompleteEvent;
import com.ecommerce.walmart.model.ServerResponse;
import com.ecommerce.walmart.repository.VerificationTokenRepository;
import com.ecommerce.walmart.service.impl.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/register")
public class RegistrationController {

    private final UserService userService;
    private final ApplicationEventPublisher publisher;
    private final VerificationTokenRepository tokenRepository;

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody userDto registrationRequest, final HttpServletRequest request){
        User user = userService.registerUser(registrationRequest);
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        return ResponseEntity.ok(
                new ServerResponse("Success! Please, check your email for to complete your registration", HttpStatus.OK.value())
        );
    }

    @GetMapping("/verifyEmail")
    public ResponseEntity<?> verifyEmail(@RequestParam("token") String token){
        VerificationToken theToken = tokenRepository.findByToken(token);
        if (theToken.getUser().isEnabled()){
            return ResponseEntity.ok(
                    new ServerResponse("This account has already been verified, please, login.", HttpStatus.BAD_REQUEST.value())
            );
        }
        String verificationResult = userService.validateToken(token);
        if (verificationResult.equalsIgnoreCase("valid")){
            return ResponseEntity.ok(
                    new ServerResponse("Email verified successfully. Now you can login to your account", HttpStatus.OK.value())
            );
        }
        return ResponseEntity.ok(
                new ServerResponse("Invalid verification token", HttpStatus.BAD_REQUEST.value())
        );
    }



    public String applicationUrl(HttpServletRequest request) {
        return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }
}