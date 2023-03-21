package com.ntneik15.selflearning.retailerapp.dto.request.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class RoleDto {
    private String name;
}
