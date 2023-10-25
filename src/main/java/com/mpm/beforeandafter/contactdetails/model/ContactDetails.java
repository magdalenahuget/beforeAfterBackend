package com.mpm.beforeandafter.contactdetails.model;

import com.mpm.beforeandafter.user.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "contact_details")
public class ContactDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "street_name")
    @Size(max = 30, message = "Street name cannot exceed 30 characters")
    private String streetName;

    @Column(name = "street_num")
    @Size(max = 10, message = "Street number cannot exceed 10 characters")
    private String streetNumber;

    @Column(name = "apart_num")
    @Size(max = 10, message = "Apartment number cannot exceed 10 characters")
    private String apartNumber;


    @Size(max = 10, message = "Postcode cannot exceed 10 characters")
    private String postcode;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(name = "webpage", length = 150)
    private String webpage;
}