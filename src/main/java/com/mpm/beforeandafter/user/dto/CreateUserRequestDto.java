package com.mpm.beforeandafter.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateUserRequestDto {

    private String userName;
    private String userEmail;
    private String userPassword;
}
