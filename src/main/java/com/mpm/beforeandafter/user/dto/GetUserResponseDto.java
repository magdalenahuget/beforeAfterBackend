package com.mpm.beforeandafter.user.dto;

import com.mpm.beforeandafter.user.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetUserResponseDto {

    private String userName;
    private String email;
    private String roleName;

    public static GetUserResponseDto map(User user){
        return GetUserResponseDto.builder()
                .userName(user.getName())
                .email(user.getEmail())
                .roleName(user.getRole().getName())
                .build();
    }
}