package com.ntneik15.selflearning.retailerapp.service;

import com.ntneik15.selflearning.retailerapp.entity.Role;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface IRoleService {
    Optional<Role> findByName(String name);
    Role save(Role role);
}
