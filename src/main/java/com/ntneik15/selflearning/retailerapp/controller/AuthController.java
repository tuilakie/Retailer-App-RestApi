package com.ntneik15.selflearning.retailerapp.controller;

import com.ntneik15.selflearning.retailerapp.dto.auth.UserDto;
import com.ntneik15.selflearning.retailerapp.dto.auth.AuthResponse;
import com.ntneik15.selflearning.retailerapp.dto.response.base.BaseResponse;
import com.ntneik15.selflearning.retailerapp.exception.BadRequestException;
import com.ntneik15.selflearning.retailerapp.exception.ConflictException;
import com.ntneik15.selflearning.retailerapp.exception.UnauthorizedException;
import com.ntneik15.selflearning.retailerapp.security.userprinciple.UserPrincipleService;
import com.ntneik15.selflearning.retailerapp.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(value = "/api/v1/auth")
@Tag(name = "Auth", description = "Auth API")
public class AuthController {
    @Autowired
    public IUserService userService;
    @Autowired
    public UserPrincipleService userPrincipleService;
    @Operation(summary ="Login")
    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                throw new BadRequestException(error.getDefaultMessage());
            });
        }
        AuthResponse loginResponse = userService.login(userDto.getUsername(), userDto.getPassword()).orElseThrow(()
                -> new UnauthorizedException("Invalid username or password"));
        return ResponseEntity.ok(loginResponse);
    }
    @Operation(summary ="Register")
    @PostMapping(value = "/register")
    public ResponseEntity<BaseResponse> register(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                throw new BadRequestException(error.getDefaultMessage());
            });
        }
        BaseResponse registerResponse = userService.register(userDto.getUsername(), userDto.getPassword()).orElseThrow(()
                -> new ConflictException("Username already exists"));

        return ResponseEntity.ok(registerResponse);
    }
}
