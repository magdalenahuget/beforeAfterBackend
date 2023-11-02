package com.mpm.beforeandafter.user.service;

import com.mpm.beforeandafter.role.type.RoleType;
import com.mpm.beforeandafter.user.dto.*;

import java.util.List;

public interface UserService {

    List<GetUserResponseDto> getUsers(RoleType roleType);

    CreateUserResponseDto createUser(CreateUserRequestDto userDto, RoleType roleType);

    GetUserResponseDto getUserById(Long userId);

    GetAboutMeResponseDto getAboutMeByUserId(Long userId);

    CreateAboutMeResponseDto updateUserByAboutMe(Long userId, CreateAboutMeRequestDto aboutMe);

    void deleteUser(Long userId);
}
