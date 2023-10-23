package com.mpm.beforeandafter.address.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

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
    private String streetName;

    @Column(name="street_num")
    private String streetNumber;

    @Column(name="apart_num")
    private String apartNumber;

    @Column(name="postcode")
    private String postcode;
}