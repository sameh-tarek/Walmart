package com.ecommerce.walmart.controller;

import com.ecommerce.walmart.dto.userDto;
import com.ecommerce.walmart.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserServiceImpl userServiceImpl;

    @GetMapping
    public List<userDto> getUsers(){
        return userServiceImpl.getUsers();
    }
}