package com.ntneik15.selflearning.retailerapp.service.impl;

import com.ntneik15.selflearning.retailerapp.dto.auth.RoleDto;
import com.ntneik15.selflearning.retailerapp.dto.auth.UserDto;
import com.ntneik15.selflearning.retailerapp.dto.auth.AuthResponse;
import com.ntneik15.selflearning.retailerapp.dto.response.base.BaseResponse;
import com.ntneik15.selflearning.retailerapp.dto.response.base.Meta;
import com.ntneik15.selflearning.retailerapp.entity.Role;
import com.ntneik15.selflearning.retailerapp.entity.User;
import com.ntneik15.selflearning.retailerapp.exception.UnauthorizedException;
import com.ntneik15.selflearning.retailerapp.mapper.UserMapper;
import com.ntneik15.selflearning.retailerapp.repository.IRoleRepository;
import com.ntneik15.selflearning.retailerapp.repository.IUserRepository;
import com.ntneik15.selflearning.retailerapp.security.jwt.JwtProvider;
import com.ntneik15.selflearning.retailerapp.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;

    public Optional<AuthResponse> login(String username, String password) {
      Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            log.info("User found");
            if (passwordEncoder.matches(password, user.get().getPassword())) {
                log.info("Password matched");
                String token = jwtProvider.generateJwtToken(user.get().getUsername());
                log.info("Token generated "+ token);
                return Optional.of(new AuthResponse(new Meta(true, "Login successfully!", HttpStatus.OK),user.get(), token));
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<BaseResponse> register(String username, String password) {
    if (userRepository.existsByUsername(username)) {
            return Optional.empty();
        }
        UserDto userDto = new UserDto(username, password, Set.of(new RoleDto("ROLE_USER")));
        return Optional.of((new BaseResponse(new Meta(true, "Register successfully!", HttpStatus.OK), null)));

    }


    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Boolean isUserHasRole(String username, String roleName) {
        User user = findByUsername(username).stream().findFirst().orElseThrow(() -> new UnauthorizedException("User not found"));
        UserDto userDto = UserMapper.toUserDto(user);
        if(!userDto.getRole().contains(new RoleDto("ROLE_ADMIN"))) {
            throw new UnauthorizedException("User not admin to delete product");
        }
        return true;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = userRepository.findByUsername(username).get();
        Role role = roleRepository.findByName(roleName).get();
        user.getRoles().add(role);
    }


}
