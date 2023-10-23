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
    private Long user_id;

    @Column(name = "user_name", length = 50, unique = true)
    @NotBlank(message = "User name is mandatory.")
    @Size(min=2, max=25,message="User name must be between 2 and 25 characters long.")
    @NotNull
    private String userName;

    @Column(name = "user_email", length = 100, unique = true)
    @NotNull
    @NotBlank(message = "User email is mandatory.")
    @Size(min=5, max=50,message="User email must be between 5 and 50 characters long.")
    private String userEmail;

    @Column(name = "user_password", length = 200)//TODO security hash?
    @NotBlank(message = "User password is mandatory.")
    @NotNull
    @Size(min=8, max=50,message="User password must be between 8 and 50 characters long.")
    private String userPassword;

    @NotNull
//    @NotBlank(message = "User role is mandatory.")
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @JsonIgnore
    private Role role;

    @Column(name = "city_name", length = 100)
    @Size(min=2, max=100,message="User password must be between 2 and 100 characters long.")
    private String cityName;

    @Column(name = "user_profile_image", unique = true)
    private String userProfileImage;

    @Column(name = "address_id")//TODO relation with table addresses oneToOne
    private int addressId;

    @Column(name = "image_id")//TODO this relation should be removed, as image has userId
//    @NotNull
//    @NotBlank
    private int imagesId;

    @Column(name = "about_me", columnDefinition = "TEXT")
    private String aboutMe;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(name = "webpage", length = 150)
    private String webpage;

    @Column(name = "favourites_id")//TODO relation ManyToMany, here should be list
    private String favouritesId;

    @Column(name = "status_id")//TODO no relation, status should be value from enum or as role model
//    @NotNull
//    @NotBlank
    private int statusId;

    @Column(name = "user_approved", columnDefinition = "BOOLEAN DEFAULT FALSE")
    @NotNull
    private boolean userApproved;

    @Column(name = "user_approved_by_user_id")
    private int userApprovedByUserId;

    @Column(name = "user_approved_date")
    private String userApprovedDate;
}