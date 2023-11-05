package com.ecommerce.walmart.controller;

import com.ecommerce.walmart.Entity.User;
import com.ecommerce.walmart.Entity.VerificationToken;
import com.ecommerce.walmart.dto.userDto;
import com.ecommerce.walmart.event.RegistrationCompleteEvent;
import com.ecommerce.walmart.event.listener.RegistrationCompleteEventListener;
import com.ecommerce.walmart.model.server.ServerResponse;
import com.ecommerce.walmart.repository.VerificationTokenRepository;
import com.ecommerce.walmart.service.impl.UserServiceImpl;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/register")
public class RegistrationController {

    private final UserServiceImpl userServiceImpl;
    private final ApplicationEventPublisher publisher;
    private final VerificationTokenRepository tokenRepository;
    private final RegistrationCompleteEventListener eventListener;
    private final HttpServletRequest servletRequest;

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody userDto registrationRequest, final HttpServletRequest request){
        User user = userServiceImpl.registerUser(registrationRequest);
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        return ResponseEntity.ok(
                new ServerResponse("Success! Please, check your email for to complete your registration", HttpStatus.OK.value())
        );
    }

    @GetMapping("/verifyEmail")
    public ResponseEntity<?> SendVerificationToken(@RequestParam("token") String token){
        String url = applicationUrl(servletRequest)+"/auth/register/resend-verification-token?token="+token;
        VerificationToken theToken = tokenRepository.findByToken(token);
        if (theToken.getUser().isEnabled()){
            return ResponseEntity.ok(
                    new ServerResponse("This account has already been verified, please, login.", HttpStatus.BAD_REQUEST.value())
            );
        }
        String verificationResult = userServiceImpl.validateToken(token);
        if (verificationResult.equalsIgnoreCase("valid")){
            return ResponseEntity.ok(
                    new ServerResponse("Email verified successfully. Now you can login to your account", HttpStatus.OK.value())
            );
        }
        return ResponseEntity.ok("Invalid verification link, <a href=\"" + url + "\"> Get a new verification link");
    }

    @GetMapping("/resend-verification-token")
    @ResponseBody
    public ResponseEntity<?> resendVerificationToken(
            @RequestParam("token") String oldToken, HttpServletRequest request)
            throws MessagingException, UnsupportedEncodingException {

        VerificationToken verificationToken = userServiceImpl.generateNewVerificationToken(oldToken);
        User theUser = verificationToken.getUser();
        resendVerificationTokenEmail(theUser, applicationUrl(request), verificationToken);

        return ResponseEntity.ok("A new verification link has been sent to your email. Please check it to complete your registration.");
    }

    private void resendVerificationTokenEmail(User theUser, String applicationUrl, VerificationToken token)
            throws MessagingException, UnsupportedEncodingException {
        String url = applicationUrl+"/auth/register/verifyEmail?token="+token.getToken();
        eventListener.sendVerificationEmail(url);
        log.info("Click the link to verify your registration :  {}", url);
    }


    public String applicationUrl(HttpServletRequest request) {
        return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }
}