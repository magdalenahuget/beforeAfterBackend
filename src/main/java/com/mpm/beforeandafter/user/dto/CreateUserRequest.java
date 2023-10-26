package com.mpm.beforeandafter.user.dto;

import lombok.Data;

@Data
public class CreateUserRequest {

    private String userName;
    private String userEmail;
    private String userPassword;
}
