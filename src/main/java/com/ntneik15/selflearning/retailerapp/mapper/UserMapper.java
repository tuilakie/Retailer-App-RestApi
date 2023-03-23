package com.ntneik15.selflearning.retailerapp.mapper;

import com.ntneik15.selflearning.retailerapp.dto.auth.UserDto;
import com.ntneik15.selflearning.retailerapp.entity.User;

import java.util.stream.Collectors;

public class UserMapper {
    public static UserDto toUserDto(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .role(user.getRoles().stream().map(RoleMapper::toDto).collect(Collectors.toSet()))
                .build();
    }

    public static User toUserEntity(UserDto userDto) {
        return User.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .roles(userDto.getRole().stream().map(RoleMapper::toEntity).collect(Collectors.toSet()))
                .build();
    }
}
