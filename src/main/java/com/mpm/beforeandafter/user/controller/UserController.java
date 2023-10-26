package com.mpm.beforeandafter.user.controller;

import com.mpm.beforeandafter.role.type.RolesType;
import com.mpm.beforeandafter.user.dto.UserAboutMeRequestDto;
import com.mpm.beforeandafter.user.dto.UserAboutMeResponseDto;
import com.mpm.beforeandafter.user.model.User;
import com.mpm.beforeandafter.user.dto.UserRequestDto;
import com.mpm.beforeandafter.user.dto.UserResponseDto;
import com.mpm.beforeandafter.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers(@RequestParam(required = false) RolesType roleType) {
        List<UserResponseDto> userResponseDtos = userService.getUsers(roleType);
        if (userResponseDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userResponseDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserRequestDto user) {
        User createdUser = userService.createUser(user);
//        UserResponseDto userResponseDto = new UserResponseDto()
//        TODO: In future return userResponseDto without password
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable("id") Long userId){
        log.debug("Getting user by id: {}", userId);
        UserResponseDto userResponseDto = userService.getUserById(userId);
        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping("/{id}about_me")
    public ResponseEntity<UserAboutMeResponseDto> getUserAboutMe(@PathVariable("id") Long userId){
        log.debug("Getting user about me by id: {}", userId);
        UserAboutMeResponseDto userAboutMeResponseDto = userService.getAboutMeByUserId(userId);
        return ResponseEntity.ok(userAboutMeResponseDto);
    }

    @PatchMapping("/{id}about_me")
    public ResponseEntity<User> updateUserAboutMe(@PathVariable("id") Long userId,
                                                       @RequestBody UserAboutMeRequestDto aboutMe) {
        log.debug("Updating user about me id: {} with data: {}", userId, aboutMe);
        User updateUserByAboutMe = userService.updateUserByAboutMe(userId, aboutMe);
        log.info("User about me updated: {}", userId);
        return ResponseEntity.ok(updateUserByAboutMe);
    }
}