package com.mpm.beforeandafter.user.dto;

import com.mpm.beforeandafter.user.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetAboutMeResponseDto {

    private String aboutMe;

    public static GetAboutMeResponseDto map(User user){
        return GetAboutMeResponseDto.builder()
                .aboutMe(user.getAboutMe())
                .build();
    }
}