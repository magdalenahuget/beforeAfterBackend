package com.mpm.beforeandafter.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Types of available roles in the system.
 */
@Getter
@AllArgsConstructor
public enum StatusType {
    IN_REVIEW("in_review"),
    USER_REVISION("user_revision"),
    ACCEPTED("accepted");

    private final String statusName;
}