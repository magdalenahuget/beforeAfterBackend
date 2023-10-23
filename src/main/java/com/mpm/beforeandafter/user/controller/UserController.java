package com.mpm.beforeandafter.user.controller;

import com.mpm.beforeandafter.role.type.RolesType;
import com.mpm.beforeandafter.user.model.User;
import com.mpm.beforeandafter.user.dto.UserRequestDto;
import com.mpm.beforeandafter.user.dto.UserResponseDto;
import com.mpm.beforeandafter.user.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> getAllUsers(@RequestParam(required = false) RolesType roleType) {
        List<UserResponseDto> userResponseDtos = userServiceImpl.getUsers(roleType);
        if (userResponseDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userResponseDtos, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody UserRequestDto user) {
        User createdUser = userServiceImpl.createUser(user);
//        UserResponseDto userResponseDto = new UserResponseDto()
//        TODO: In future return userResponseDto without password
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}