package com.mpm.beforeandafter.role.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Types of available roles in the system.
 */
@Getter
@AllArgsConstructor
public enum RolesType {
    ADMIN("admin"),
    USER("user");

    private final String roleName;

}
