package com.myecom.myapp.controller;

import com.myecom.myapp.domain.dto.user.UserDto;
import com.myecom.myapp.domain.dto.user.UserLoginDto;
import com.myecom.myapp.domain.dto.user.UserLoginResponse;
import com.myecom.myapp.domain.entity.user.User;
import com.myecom.myapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.registerUser(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> loginUser(@Valid @RequestBody UserLoginDto loginDto) {
        String token = userService.loginUser(loginDto);
        if (Objects.nonNull(token)) {
            return ResponseEntity.ok(UserLoginResponse.builder().jwt(token).build());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).build();
    }

    @GetMapping("/userDetails")
    public User getUserDetails(@AuthenticationPrincipal User user){
        return user;

    }
}
