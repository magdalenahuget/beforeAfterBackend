package com.mpm.beforeandafter.contactdetails.dto;

import com.mpm.beforeandafter.contactdetails.model.ContactDetails;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateContactDetailsResponseDTO {
    private long contactId;
    private String streetName;
    private String streetNumber;
    private String apartNumber;
    private String postcode;
    private String phoneNumber;
    private String webpage;

    public static CreateContactDetailsResponseDTO map(ContactDetails contactDetails){
        return CreateContactDetailsResponseDTO.builder()
                .contactId(contactDetails.getId())
                .streetName(contactDetails.getStreetName())
                .streetNumber(contactDetails.getStreetNumber())
                .apartNumber(contactDetails.getApartNumber())
                .postcode(contactDetails.getPostcode())
                .phoneNumber(contactDetails.getPhoneNumber())
                .webpage(contactDetails.getWebpage())
                .build();
    }
}
