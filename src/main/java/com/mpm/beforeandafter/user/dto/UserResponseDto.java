package com.mpm.beforeandafter.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserResponseDto {

    private String userName;
    private String email;
    private String roleName;
}