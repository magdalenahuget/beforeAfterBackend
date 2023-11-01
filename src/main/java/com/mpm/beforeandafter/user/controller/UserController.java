package com.mpm.beforeandafter.user.controller;

import com.mpm.beforeandafter.role.type.RolesType;
import com.mpm.beforeandafter.user.dto.CreateUserResponse;
import com.mpm.beforeandafter.user.dto.CreateAboutMeRequest;
import com.mpm.beforeandafter.user.dto.CreateAboutMeResponse;
import com.mpm.beforeandafter.user.model.User;
import com.mpm.beforeandafter.user.dto.CreateUserRequest;
import com.mpm.beforeandafter.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("api/v1/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<CreateUserResponse> getAllUsers(@RequestParam(required = false) RolesType roleType) {
        List<User> users = userService.getUsers(roleType);
        List<CreateUserResponse> createUserResponses = new ArrayList<>();
        for (User user : users) {
            CreateUserResponse createUserResponse = CreateUserResponse.map(user);
            createUserResponses.add(createUserResponse);
        }
        return createUserResponses;
    }

    @PostMapping
    public CreateUserResponse createUser(@RequestBody CreateUserRequest user) {
        User createdUser = userService.createUser(user, RolesType.USER);
        return CreateUserResponse.map(createdUser);
    }

    @GetMapping("{id}")
    public CreateUserResponse getUserById(@PathVariable("id") Long userId){
        log.debug("Getting user by id: {}", userId);
        User userById = userService.getUserById(userId);
        return CreateUserResponse.map(userById);
    }

    @GetMapping("/{id}/about_me")
    public CreateAboutMeResponse getUserAboutMe(@PathVariable("id") Long userId){
        log.debug("Getting user about me by id: {}", userId);
        User userWithAboutMe = userService.getAboutMeByUserId(userId);
        return CreateAboutMeResponse.map(userWithAboutMe);
    }

    @PatchMapping("/{id}/about_me")
    public CreateAboutMeResponse updateUserAboutMe(@PathVariable("id") Long userId,
                                                       @RequestBody CreateAboutMeRequest aboutMe) {
        log.debug("Updating about me of user with id: {} with data: {}", userId, aboutMe);
        User userWithUpdatedAboutMe = userService.updateUserByAboutMe(userId, aboutMe);
        log.info("User about me updated: {}", userId);
        return CreateAboutMeResponse.map(userWithUpdatedAboutMe);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
    }

}