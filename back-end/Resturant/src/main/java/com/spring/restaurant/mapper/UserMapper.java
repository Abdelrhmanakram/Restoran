package com.spring.restaurant.mapper;

import com.spring.restaurant.model.userModel.User;
import com.spring.restaurant.service.dto.jwt.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper user_Mapper = Mappers.getMapper(UserMapper.class);

    User toEntity(UserDto userDto);
    List<User> toEntityList(List<UserDto> userDtoList);


    @Mapping(target ="roles", ignore = true)
//    @Mapping(target ="requestOrders", ignore = true)
    UserDto toDto(User user);
    List<UserDto> toDtoList( List<User> userList);
}
