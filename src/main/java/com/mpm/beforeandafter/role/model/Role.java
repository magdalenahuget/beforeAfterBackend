package com.mpm.beforeandafter.role.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class representing a role in the system.
 */
@Setter
@Getter
@NoArgsConstructor

@Entity
@Table(name = "roles", indexes = @Index(name = "role_name_index", columnList = "role_name"))

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int roleId;

    @Column(name = "role_name", length = 30, unique = true)
    @NotBlank
    @NotNull
    private String roleName;

    public Role(String roleName) {
        this.roleName = roleName;
    }
}
