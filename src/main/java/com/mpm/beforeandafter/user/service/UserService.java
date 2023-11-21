package com.mpm.beforeandafter.user.service;

import com.mpm.beforeandafter.role.type.RoleType;
import com.mpm.beforeandafter.user.dto.*;
import com.mpm.beforeandafter.user.dto.UserRequest;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {

    List<GetUserResponseDto> getUsers(RoleType roleType);

    CreateUserResponseDto createUser(CreateUserRequestDto userDto, RoleType roleType);

    GetUserResponseDto getUserById(Long userId);

    GetAboutMeResponseDto getAboutMeByUserId(Long userId);

    CreateAboutMeResponseDto updateUserByAboutMe(Long userId, CreateAboutMeRequestDto aboutMe);

    void deleteUser(Long userId);

    Authentication getAuthentication(UserRequest loginRequest);

    String getSecurityContextAndJwt(UserRequest loginRequest, Authentication authentication);

    org.springframework.security.core.userdetails.User getUserDetails(Authentication authentication);

    List<String> getRoles(org.springframework.security.core.userdetails.User userDetails);

    JwtResponse createJwtResponse(String jwt, String username, List<String> roles);
}
