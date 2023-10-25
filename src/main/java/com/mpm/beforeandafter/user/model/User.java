package com.mpm.beforeandafter.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mpm.beforeandafter.role.model.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, unique = true)
    @NotBlank(message = "User name is mandatory.")
    @Size(min=2, max=25,message="User name must be between 2 and 25 characters long.")
    @NotNull
    private String name;

    @Column(name = "email", length = 100, unique = true)
    @NotNull
    @NotBlank(message = "User email is mandatory.")
    @Size(min=5, max=50,message="User email must be between 5 and 50 characters long.")
    private String email;

    @Column(name = "password", length = 200)//TODO security hash?
    @NotBlank(message = "User password is mandatory.")
    @NotNull
    @Size(min=8, max=50,message="User password must be between 8 and 50 characters long.")
    private String password;

    @NotNull(message = "User role is mandatory.")
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @JsonIgnore
    private Role role;

    @Column(name = "city_name", length = 100)
    @Size(min=2, max=100,message="User password must be between 2 and 100 characters long.")
    private String cityName;

    @Column(name = "avatar", unique = true)
    private String avatar; //TODO:change type to proper type for images

    @Column(name = "about_me", columnDefinition = "TEXT")
    @Size(min = 10, max = 500, message = "About me must be between 10 and 500 characters.")
    @Lob
    private String aboutMe;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @NotNull(message = "Status is mandatory.")
    private StatusType status;

    @Column(name = "is_approved", columnDefinition = "BOOLEAN DEFAULT FALSE")
    @NotNull(message = "Approved status is mandatory.")
    private boolean isApproved;

    @Column(name = "approved_by_user_id", nullable = true)
    private int approvedByUserId;

    @Column(name = "approved_date", nullable = true)
    private String approvedDate;
}