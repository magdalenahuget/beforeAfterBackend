package com.mpm.beforeandafter.user.service;

import com.mpm.beforeandafter.role.type.RolesType;
import com.mpm.beforeandafter.user.dto.UserAboutMeRequestDto;
import com.mpm.beforeandafter.user.dto.UserAboutMeResponseDto;
import com.mpm.beforeandafter.user.dto.UserRequestDto;
import com.mpm.beforeandafter.user.dto.UserResponseDto;
import com.mpm.beforeandafter.user.model.User;

import java.util.List;

public interface UserService {

    List<UserResponseDto> getUsers(RolesType roleType);

    User createUser(UserRequestDto userDto);

    UserResponseDto getUserById(Long userId);
    UserAboutMeResponseDto getAboutMeByUserId(Long userId);

    User updateUserByAboutMe(Long userId, UserAboutMeRequestDto aboutMe);
}
