package com.mpm.beforeandafter.user.controller;

import com.mpm.beforeandafter.role.type.RolesType;
import com.mpm.beforeandafter.user.dto.*;
import com.mpm.beforeandafter.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<GetUserResponseDto> getAllUsers(@RequestParam(required = false) RolesType roleType) {
        return userService.getUsers(roleType);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateUserResponseDto createUser(@RequestBody CreateUserRequestDto user) {
        return userService.createUser(user, RolesType.USER);
    }

    @GetMapping("{id}")
    public GetUserResponseDto getUserById(@PathVariable("id") Long userId){
        log.debug("Getting user by id: {}", userId);
        return userService.getUserById(userId);
    }

    @GetMapping("/{id}/about_me")
    public GetAboutMeResponseDto getUserAboutMe(@PathVariable("id") Long userId){
        log.debug("Getting user about me by id: {}", userId);
        return userService.getAboutMeByUserId(userId);
    }

    @PatchMapping("/{id}/about_me")
    public CreateAboutMeResponseDto updateUserAboutMe(@PathVariable("id") Long userId,
                                                      @RequestBody CreateAboutMeRequestDto aboutMe) {
        log.debug("Updating about me of user with id: {} with data: {}", userId, aboutMe);
        CreateAboutMeResponseDto userWithUpdatedAboutMe = userService.updateUserByAboutMe(userId, aboutMe);
        log.info("User about me updated: {}", userId);
        return userWithUpdatedAboutMe;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
    }
}