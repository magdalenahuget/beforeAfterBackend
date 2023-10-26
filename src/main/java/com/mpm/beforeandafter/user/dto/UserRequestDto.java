package com.mpm.beforeandafter.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestDto {

    private String userName;
    private String userEmail;
    private String userPassword;
}
