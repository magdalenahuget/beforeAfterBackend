package com.mpm.beforeandafter.contactdetails.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateContactDetailsResponseDto {
    private long contactId;
    private String streetName;
    private String streetNumber;
    private String apartNumber;
    private String postcode;
    private String phoneNumber;
    private String webpage;
}
