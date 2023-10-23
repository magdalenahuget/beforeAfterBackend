package com.mpm.beforeandafter.user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(name = "user_name", length = 50, unique = true)
    @NotBlank
    @NotNull
    private String userName;

    @Column(name = "user_email", length = 100, unique = true)
    @NotNull
    @NotBlank
    private String userEmail;

    @Column(name = "user_password", length = 100, unique = true)//TODO co zabezpieczeniem?
    @NotBlank
    @NotNull
    private String userPassword;

    @Column(name = "role_id")//TODO polaczenie z tabelą roles oneToMany
    @NotNull
    @NotBlank
    private int roleId;

    @Column(name = "city_name", length = 100)
    private String cityName;

    @Column(name = "user_profile_image", unique = true)
    private String userProfileImage;

    @Column(name = "address_id")//TODO polaczeniez tabelą addreesses oneToOne
    private int addressId;

    @Column(name = "image_id")//TODO polaczenie z tabela images oneToMAny
    @NotNull
    @NotBlank
    private int imagesId;

    @Column(name = "about_me", columnDefinition = "TEXT")
    private String aboutMe;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(name = "webpage", length = 150)
    private String webpage;

    @Column(name = "favourites_id")//TODO polaczenie z tabela favourites oneToMany
    private String favouritesId;

    @Column(name = "status_id")//TODO polaczenie z tabela statuses
    @NotNull
    @NotBlank
    private int statusId;

    @Column(name = "user_approved", columnDefinition = "BOOLEAN")
    @NotNull
    @NotBlank
    private boolean userApproved;

    @Column(name = "user_approved_by_user_id")
    private int userApprovedByUserId;

    @Column(name = "user_approved_date")
    private String userApprovedDate;


}
