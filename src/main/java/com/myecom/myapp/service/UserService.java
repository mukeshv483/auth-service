package com.myecom.myapp.service;


import com.myecom.myapp.domain.dto.user.UserDto;
import com.myecom.myapp.domain.dto.user.UserLoginDto;
import com.myecom.myapp.domain.entity.user.User;
import com.myecom.myapp.exception.UserAlreadyExistException;
import com.myecom.myapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EncryptionService encryptionService;
    private final JWTService jwtService;

    private static final int SALT_LENGTH = 16;

    public User registerUser(UserDto userDto) {

        if (userRepository.findByUserName(userDto.getUserName()).isPresent() ||
                userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new UserAlreadyExistException("User Already Present!!");
        }
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(encryptionService.encryptPassword(userDto.getPassword()));
        user.setAddresses(userDto.getAddress());

        return userRepository.save(user);
    }

    public String loginUser(UserLoginDto loginDto) {
        Optional<User> userOptional = userRepository.findByUserName(loginDto.getUserName());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (encryptionService.verifyPassword(loginDto.getPassword(), user.getPassword())) {
                return jwtService.generateJWTToken(user);
            }

        }
        return null;
    }
}
