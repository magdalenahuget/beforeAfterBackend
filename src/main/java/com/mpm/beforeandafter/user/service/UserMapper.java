package com.mpm.beforeandafter.user.service;

import com.mpm.beforeandafter.user.dto.*;
import com.mpm.beforeandafter.user.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    CreateUserResponseDto mapToCreateUserResponseDto(User user) {
        return CreateUserResponseDto.builder()
                .userName(user.getName())
                .email(user.getEmail())
                .roleName(user.getRole().getName())
                .build();
    }

    CreateAboutMeResponseDto mapToCreateAboutMeResponseDto(User user) {
        return CreateAboutMeResponseDto.builder()
                .aboutMe(user.getAboutMe())
                .build();
    }

    public GetUserResponseDto mapToGetUserResponseDto(User user) {
        return GetUserResponseDto.builder()
                .userName(user.getName())
                .email(user.getEmail())
                .roleName(user.getRole().getName())
                .avatar(user.getAvatar())
                .build();
    }

    GetAboutMeResponseDto mapToGetAboutMeResponseDto(User user) {
        return GetAboutMeResponseDto.builder()
                .aboutMe(user.getAboutMe())
                .build();
    }

    CreateAvatarResponseDto mapToCreateAvatarResponseDto(User user){
        return CreateAvatarResponseDto.builder()
                .avatar(user.getAvatar())
                .build();
    }
}
