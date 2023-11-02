package com.mpm.beforeandafter.contactdetails.service;

import com.mpm.beforeandafter.contactdetails.dto.CreateContactDetailsRequestDto;
import com.mpm.beforeandafter.contactdetails.dto.CreateContactDetailsResponseDto;
import com.mpm.beforeandafter.contactdetails.dto.GetContactDetailsResponseDto;

public interface ContactDetailsService {

    CreateContactDetailsResponseDto createContactDetails(CreateContactDetailsRequestDto request);
//    CreateContactDetailsResponseDto modifiedContactDetails(CreateContactDetailsRequestDto request);

    GetContactDetailsResponseDto getContactDetailsByUserId(Long userId);
}
