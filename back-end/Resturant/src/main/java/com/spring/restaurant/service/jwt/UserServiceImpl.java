package com.spring.restaurant.service.jwt;

import com.spring.restaurant.config.jwt.TokenHandler;
import com.spring.restaurant.mapper.UserMapper;
import com.spring.restaurant.model.userModel.Role;
import com.spring.restaurant.model.userModel.User;
import com.spring.restaurant.repository.jwt.RoleRepository;
import com.spring.restaurant.repository.jwt.UserRepository;
import com.spring.restaurant.service.dto.jwt.UserDto;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TokenHandler tokenHandler;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User getUserbyEmail(String email) throws SystemException {
        User user = userRepository.findByEmail(email);

        if (user == null) {

            throw new RuntimeException("error.invalid.email");
        }
        return user;
    }

    @Override
    public User checkUserExistByToken(String token) throws SystemException {
        String email = tokenHandler.getSubject(token);

        if (Objects.isNull(email)) {
            return null;
        }
        return  userRepository.findByEmail(email);
    }

    @Override
    public void createUser(UserDto userDto) throws SystemException {

        if (userDto.getId() != null) {
            throw new SystemException("id must be null");
        }

        User userExsits = userRepository.findByEmail(userDto.getEmail());
        if (userExsits != null) {
            throw new RuntimeException("error.clientExist");
        }

        User user = UserMapper.user_Mapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findByCode("ROLE_USER");
        if (role == null) {
            throw new SystemException("role not exist");
        }
        List<Role> roles = List.of(role);
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public UserDto getUserById(Long id) {

        User users = userRepository.findById(id).get();
        return  UserMapper.user_Mapper.toDto(users);
    }
}
