package com.mpm.beforeandafter.user.dto;

import com.mpm.beforeandafter.user.model.User;
import lombok.*;

@Data
@Builder
public class CreateAboutMeResponseDto {

    private String aboutMe;

    public static CreateAboutMeResponseDto map(User user){
        return CreateAboutMeResponseDto.builder()
                .aboutMe(user.getAboutMe())
                .build();
    }
}