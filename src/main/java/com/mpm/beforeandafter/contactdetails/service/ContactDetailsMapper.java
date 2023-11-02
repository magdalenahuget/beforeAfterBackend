package com.mpm.beforeandafter.contactdetails.service;

import com.mpm.beforeandafter.contactdetails.dto.CreateContactDetailsResponseDto;
import com.mpm.beforeandafter.contactdetails.dto.GetContactDetailsResponseDto;
import com.mpm.beforeandafter.contactdetails.model.ContactDetails;
import org.springframework.stereotype.Component;

@Component
public class ContactDetailsMapper {

    CreateContactDetailsResponseDto mapToCreateContactDetailsResponseDto(ContactDetails contactDetails){
        return CreateContactDetailsResponseDto.builder()
                .contactId(contactDetails.getId())
                .streetName(contactDetails.getStreetName())
                .streetNumber(contactDetails.getStreetNumber())
                .apartNumber(contactDetails.getApartNumber())
                .postcode(contactDetails.getPostcode())
                .phoneNumber(contactDetails.getPhoneNumber())
                .webpage(contactDetails.getWebpage())
                .build();
    }

    GetContactDetailsResponseDto mapToGetContactDetailsResponseDto(ContactDetails contactDetails) {
        return GetContactDetailsResponseDto.builder()
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
