package com.mpm.beforeandafter.user.dto;

import com.mpm.beforeandafter.user.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserResponse {

    private String userName;
    private String email;
    private String roleName;

    public static CreateUserResponse map(User user){
        return CreateUserResponse.builder()
                .userName(user.getName())
                .email(user.getEmail())
                .roleName(user.getRole().getName())
                .build();
    }
}