package com.mpm.beforeandafter.status.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "roles", indexes = @Index(name = "role_name_index",columnList = "role_name"))

public class Role{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private long roleId;

    @Column(name = "role_name", length = 50, unique = true)
    @NotBlank
    @NotNull
    private String roleName;
}
