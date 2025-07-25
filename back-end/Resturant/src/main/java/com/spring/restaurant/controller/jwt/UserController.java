package com.spring.restaurant.controller.jwt;

import com.spring.restaurant.service.dto.jwt.TokenDto;
import com.spring.restaurant.service.dto.jwt.UserDto;
import com.spring.restaurant.service.dto.jwt.UserLoginDto;
import com.spring.restaurant.service.jwt.AuthService;
import com.spring.restaurant.service.jwt.UserService;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService clientService;

    @PostMapping("/login")
    ResponseEntity<TokenDto> login(@RequestBody UserLoginDto userLoginDto) throws SystemException {
        return ResponseEntity.ok(authService.login(userLoginDto));
    }

    @PostMapping("/create-client")
    ResponseEntity<Void> createUser(@RequestBody UserDto userDto) throws SystemException {
        clientService.createUser(userDto);
        return  ResponseEntity.created(URI.create("/user/addUserWithRole")).build();
    }
}
