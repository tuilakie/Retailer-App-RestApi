package com.ntneik15.selflearning.retailerapp.service;

import com.ntneik15.selflearning.retailerapp.dto.response.auth.AuthResponse;
import com.ntneik15.selflearning.retailerapp.dto.response.base.BaseResponse;
import com.ntneik15.selflearning.retailerapp.entity.User;

import java.util.Optional;

public interface IUserService {
    Optional<User> findByUsername(String username);
    Optional<AuthResponse> login(String username, String password);
    Optional<BaseResponse> register(String username, String password);
    Boolean existsByUsername(String username);
    User save(User user);
    Boolean isUserHasRole(String username, String roleName);
    void addRoleToUser(String username, String roleName);
}
