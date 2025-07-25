package com.spring.restaurant.service.jwt;

import com.spring.restaurant.model.userModel.User;
import com.spring.restaurant.service.dto.jwt.UserDto;
import jakarta.transaction.SystemException;

public interface UserService {

    User getUserbyEmail(String email) throws SystemException;
    User checkUserExistByToken(String token) throws SystemException;
    void createUser (UserDto clientDto) throws SystemException;
    UserDto getUserById( Long id);
}
