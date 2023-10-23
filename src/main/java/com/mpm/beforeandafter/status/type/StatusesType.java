package com.mpm.beforeandafter.status.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Types of available roles in the system.
 */
@Getter
@AllArgsConstructor
public enum StatusesType {
    IN_REVIEW("in_review"),
    USER_REVISION("user_revision"),
    ACCEPTED("accepted");

    private final String statusName;
}