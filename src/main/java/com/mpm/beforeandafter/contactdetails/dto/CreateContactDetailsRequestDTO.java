package com.mpm.beforeandafter.contactdetails.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateContactDetailsRequestDTO {
    private String streetName;
    private String streetNumber;
    private String apartNumber;
    private String postcode;
    private String phoneNumber;
    private String webpage;
    private long userId;
}
