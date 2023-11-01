package com.mpm.beforeandafter.contactdetails.service;

import com.mpm.beforeandafter.contactdetails.dto.CreateContactDetailsRequestDTO;
import com.mpm.beforeandafter.contactdetails.dto.CreateContactDetailsResponseDTO;

public interface ContactDetailsService {
    CreateContactDetailsResponseDTO createContactDetails(CreateContactDetailsRequestDTO request);
//    CreateContactDetailsResponseDTO modifiedContactDetails(CreateContactDetailsRequestDTO request);

}
