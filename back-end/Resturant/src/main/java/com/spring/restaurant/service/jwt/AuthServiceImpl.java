package com.spring.restaurant.service.jwt;

import com.spring.restaurant.config.jwt.TokenHandler;
import com.spring.restaurant.model.userModel.User;
import com.spring.restaurant.service.dto.jwt.TokenDto;
import com.spring.restaurant.service.dto.jwt.UserLoginDto;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenHandler tokenHandler;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public TokenDto login(UserLoginDto userLoginDto) throws SystemException {
        User user = userService.getUserbyEmail(userLoginDto.getEmail());

        if (!passwordEncoder.matches(userLoginDto.getPassword(),user.getPassword())) {
            throw new BadCredentialsException("error.userNotExist");
        }

        return new TokenDto(tokenHandler.createToken(user));
    }

}
