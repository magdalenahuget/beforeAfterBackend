package com.mpm.beforeandafter.user.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
public class CreateUserResponseDto {

    private String userName;
    private String email;
    private List<String> roles;
}