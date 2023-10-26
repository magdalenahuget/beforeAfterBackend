package com.mpm.beforeandafter.user.mapper;

import com.mpm.beforeandafter.role.model.Role;
import com.mpm.beforeandafter.user.dto.UserAboutMeResponseDto;
import com.mpm.beforeandafter.user.dto.UserResponseDto;
import com.mpm.beforeandafter.user.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto mapUserToDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        Role userRole = user.getRole();
        userResponseDto.setUserName(user.getName());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setRoleName(userRole.getName());

        return userResponseDto;
    }

    public UserAboutMeResponseDto mapUserToAboutMeDto(User user) {
        UserAboutMeResponseDto userAboutMeResponseDto = new UserAboutMeResponseDto();
        userAboutMeResponseDto.setAboutMe(user.getAboutMe());
        return userAboutMeResponseDto;
    }
}
