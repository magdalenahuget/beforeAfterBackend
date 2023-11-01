package com.mpm.beforeandafter.contactdetails.service;

import com.mpm.beforeandafter.contactdetails.dto.CreateContactDetailsRequestDTO;
import com.mpm.beforeandafter.contactdetails.dto.CreateContactDetailsResponseDTO;
import com.mpm.beforeandafter.contactdetails.model.ContactDetails;
import com.mpm.beforeandafter.contactdetails.repository.ContactDetailsRepository;
import com.mpm.beforeandafter.user.repository.UserRepository;
import org.springframework.stereotype.Service;

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
}
