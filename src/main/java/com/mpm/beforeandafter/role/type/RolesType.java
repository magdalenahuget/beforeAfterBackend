package com.mpm.beforeandafter.role.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RolesType {
    ADMIN("admin"),
    USER("user");

    private final String roleName;

}
