package com.mpm.beforeandafter.address.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    @Column(name="street_name")
    @NotBlank(message = "Street name cannot be blank")
    @Size(max = 30, message = "Street name cannot exceed 30 characters")
    @NonNull
    private String streetName;

    @Column(name="street_num")
    @NotBlank(message = "Street number cannot be blank")
    @Size(max = 10, message = "Street number cannot exceed 10 characters")
    @NonNull
    private String streetNumber;

    @Column(name="apart_num")
    @Size(max = 10, message = "Apartment number cannot exceed 10 characters")
    private String apartNumber;

    @Column(name="postcode")
    @NotBlank(message = "Postcode cannot be blank")
    @Size(max = 10, message = "Postcode cannot exceed 10 characters")
    @NonNull
    private String postcode;
}