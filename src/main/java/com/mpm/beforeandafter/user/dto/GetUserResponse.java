package com.mpm.beforeandafter.user.dto;

import com.mpm.beforeandafter.user.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetUserResponse {

    private String userName;
    private String email;
    private String roleName;

    public static GetUserResponse map(User user){
        return GetUserResponse.builder()
                .userName(user.getName())
                .email(user.getEmail())
                .roleName(user.getRole().getName())
                .build();
    }
}