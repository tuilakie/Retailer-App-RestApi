package com.ntneik15.selflearning.retailerapp.mapper;

import com.ntneik15.selflearning.retailerapp.dto.request.auth.RoleDto;
import com.ntneik15.selflearning.retailerapp.entity.Role;

public class RoleMapper {
    public static RoleDto toDto(Role role) {
        return RoleDto.builder()
                .name(role.getName())
                .build();
    }
    public static Role toEntity(RoleDto roleDto) {
        return Role.builder()
                .name(roleDto.getName())
                .build();
    }
}
