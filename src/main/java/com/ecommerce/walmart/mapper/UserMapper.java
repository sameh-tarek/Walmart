package com.ecommerce.walmart.mapper;

import com.ecommerce.walmart.Entity.User;
import com.ecommerce.walmart.dto.userDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(userDto dto);
    @Mapping(target = "password", ignore = true)
    userDto toUserDto(User user);
    List<userDto> toUserDtos(List<User> users);
    void updateUser(@MappingTarget User target, User source);
}
