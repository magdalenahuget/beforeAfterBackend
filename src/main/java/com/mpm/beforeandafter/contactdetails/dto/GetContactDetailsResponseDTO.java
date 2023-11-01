package com.mpm.beforeandafter.contactdetails.dto;

import com.mpm.beforeandafter.contactdetails.model.ContactDetails;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetContactDetailsResponseDTO {
    private long id;
    private String streetName;
    private String streetNumber;
    private String apartNumber;
    private String postcode;
    private String phoneNumber;
    private String webpage;

    public static GetContactDetailsResponseDTO map(ContactDetails contactDetails) {
        return GetContactDetailsResponseDTO.builder()
                .id(contactDetails.getId())
                .streetName(contactDetails.getStreetName())
                .streetNumber(contactDetails.getStreetNumber())
                .apartNumber(contactDetails.getApartNumber())
                .postcode(contactDetails.getPostcode())
                .phoneNumber(contactDetails.getPhoneNumber())
                .webpage(contactDetails.getWebpage())
                .build();
    }
}
