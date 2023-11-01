package com.mpm.beforeandafter.user.service;

import com.mpm.beforeandafter.role.type.RolesType;
import com.mpm.beforeandafter.user.dto.*;

import java.util.List;

public interface UserService {

    List<GetUserResponseDto> getUsers(RolesType roleType);

    CreateUserResponseDto createUser(CreateUserRequestDto userDto, RolesType roleType);

    GetUserResponseDto getUserById(Long userId);

    GetAboutMeResponseDto getAboutMeByUserId(Long userId);

    CreateAboutMeResponseDto updateUserByAboutMe(Long userId, CreateAboutMeRequestDto aboutMe);

    void deleteUser(Long userId);
}
