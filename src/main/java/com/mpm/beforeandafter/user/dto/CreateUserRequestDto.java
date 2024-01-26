package com.mpm.beforeandafter.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequestDto {

    private String userName;
    private String userEmail;
    private String userPassword;
}
