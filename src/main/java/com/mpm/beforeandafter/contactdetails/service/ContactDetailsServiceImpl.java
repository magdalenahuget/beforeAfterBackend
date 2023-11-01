package com.mpm.beforeandafter.contactdetails.service;

import com.mpm.beforeandafter.contactdetails.dto.CreateContactDetailsRequestDTO;
import com.mpm.beforeandafter.contactdetails.dto.CreateContactDetailsResponseDTO;
import com.mpm.beforeandafter.contactdetails.dto.GetContactDetailsResponseDTO;
import com.mpm.beforeandafter.contactdetails.model.ContactDetails;
import com.mpm.beforeandafter.contactdetails.repository.ContactDetailsRepository;
import com.mpm.beforeandafter.exception.ResourceNotFoundException;
import com.mpm.beforeandafter.user.model.User;
import com.mpm.beforeandafter.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ContactDetailsServiceImpl implements ContactDetailsService {

    private final ContactDetailsRepository contactDetailsRepository;
    private final UserRepository userRepository;

    public ContactDetailsServiceImpl(ContactDetailsRepository contactDetailsRepository, UserRepository userRepository) {
        this.contactDetailsRepository = contactDetailsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CreateContactDetailsResponseDTO createContactDetails(CreateContactDetailsRequestDTO request) {
        ContactDetails contactDetails = new ContactDetails();
        contactDetails.setUser(userRepository.getReferenceById(request.getUserId()));
        contactDetails.setStreetName(request.getStreetName());
        contactDetails.setStreetNumber(request.getStreetNumber());
        contactDetails.setApartNumber(request.getApartNumber());
        contactDetails.setPostcode(request.getPostcode());
        contactDetails.setPhoneNumber(request.getPhoneNumber());
        contactDetails.setWebpage(request.getWebpage());
        contactDetailsRepository.save(contactDetails);

        return CreateContactDetailsResponseDTO.map(contactDetails);
    }

    @Override
    public GetContactDetailsResponseDTO getContactDetailsByUserId(Long userId) {
        log.info("Getting user with id: {}", userId);
        User user = userRepository.findById(userId).orElseThrow(() -> {
            log.error("User not found with given id: " + userId);
            return new ResourceNotFoundException("User not found with given id: " + userId);
        });
        log.info("Successfully fetched user with id: {}", userId);

        log.info("Getting contact details by user with id: {}", userId);
        ContactDetails contactDetails = contactDetailsRepository
                .findContactDetailsByUser(user);
        if(contactDetails == null){
            log.error("Contact details not found for user with given id: " + userId);
            throw new ResourceNotFoundException("Contact details not found for user with given id: " + userId);
        }
        log.info("Successfully fetched contact details for user with given id: {}", userId);
        return GetContactDetailsResponseDTO.map(contactDetails);
    }
}
