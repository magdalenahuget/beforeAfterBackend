package com.mpm.beforeandafter.user.controller;

import com.mpm.beforeandafter.exception.ResourceNotFoundException;
import com.mpm.beforeandafter.role.type.RoleType;
import com.mpm.beforeandafter.user.dto.CreateUserRequestDto;
import com.mpm.beforeandafter.user.dto.CreateUserResponseDto;
import com.mpm.beforeandafter.user.dto.JwtResponse;
import com.mpm.beforeandafter.user.dto.UserRequest;
import com.mpm.beforeandafter.user.repository.UserRepository;
import com.mpm.beforeandafter.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public AuthController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateUserResponseDto createUser(@RequestBody CreateUserRequestDto user) {
        System.out.println(user);
        return userService.createUser(user, RoleType.ROLE_USER);
    }

    @PostMapping("/signin")
    public JwtResponse authenticateUser(@RequestBody UserRequest loginRequest) {
        Authentication authentication = userService.getAuthentication(loginRequest);



        String jwt = userService.getSecurityContextAndJwt(loginRequest, authentication);
        User userDetails = userService.getUserDetails(authentication);
        List<String> roles = userService.getRoles(userDetails);
        return userService.createJwtResponse(jwt, userDetails.getUsername(), roles);
    }
}
