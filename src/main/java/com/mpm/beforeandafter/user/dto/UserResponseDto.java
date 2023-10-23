package com.mpm.beforeandafter.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDto {

    private String userName;
    private String email;
    private String password;
    private String roleName;

    public UserResponseDto(String userName, String email, String password, String roleName) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.roleName = roleName;
    }
}