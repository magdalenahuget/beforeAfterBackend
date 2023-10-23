package com.mpm.beforeandafter.role.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mpm.beforeandafter.user.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * Class representing a role in the system.
 */
@Setter
@Getter
@NoArgsConstructor

@Entity
@Table(name = "roles", indexes = @Index(name = "role_name_index", columnList = "name"))

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", length = 30, unique = true)
    @NotBlank
    @NotNull
    private String name;

    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private Set<User> users;

    public Role(String roleName) {
        this.name = roleName;
    }
}