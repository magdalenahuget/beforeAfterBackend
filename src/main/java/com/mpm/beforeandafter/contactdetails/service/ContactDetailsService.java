package com.mpm.beforeandafter.contactdetails.service;

import com.mpm.beforeandafter.contactdetails.dto.CreateContactDetailsRequestDTO;
import com.mpm.beforeandafter.contactdetails.dto.CreateContactDetailsResponseDTO;
import com.mpm.beforeandafter.contactdetails.dto.GetContactDetailsResponseDTO;

public interface ContactDetailsService {

    CreateContactDetailsResponseDTO createContactDetails(CreateContactDetailsRequestDTO request);
//    CreateContactDetailsResponseDTO modifiedContactDetails(CreateContactDetailsRequestDTO request);

    GetContactDetailsResponseDTO getContactDetailsByUserId(Long userId);
}
