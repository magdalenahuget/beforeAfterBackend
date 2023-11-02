package com.mpm.beforeandafter.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetUserResponseDto {

    private String userName;
    private String email;
    private String roleName;
}