package com.spring.restaurant.service.jwt;


import com.spring.restaurant.service.dto.jwt.TokenDto;
import com.spring.restaurant.service.dto.jwt.UserLoginDto;
import jakarta.transaction.SystemException;

public interface AuthService {

    TokenDto login(UserLoginDto userLoginDto) throws SystemException;
}
