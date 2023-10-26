package com.mpm.beforeandafter.user.dto;

import com.mpm.beforeandafter.user.model.User;
import lombok.*;

@Data
@Builder
public class CreateAboutMeResponse {

    private String aboutMe;

    public static CreateAboutMeResponse map(User user){
        return CreateAboutMeResponse.builder()
                .aboutMe(user.getAboutMe())
                .build();
    }
}