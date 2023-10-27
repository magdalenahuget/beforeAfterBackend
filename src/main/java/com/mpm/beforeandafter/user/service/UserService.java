package com.mpm.beforeandafter.user.service;

import com.mpm.beforeandafter.role.type.RolesType;
import com.mpm.beforeandafter.user.dto.CreateAboutMeRequest;
import com.mpm.beforeandafter.user.dto.CreateUserRequest;
import com.mpm.beforeandafter.user.model.User;

import java.util.List;

public interface UserService {

    List<User> getUsers(RolesType roleType);

    User createUser(CreateUserRequest userDto, RolesType roleType);

    User getUserById(Long userId);

    User getAboutMeByUserId(Long userId);

    User updateUserByAboutMe(Long userId, CreateAboutMeRequest aboutMe);
}
