package com.mpm.beforeandafter.contactdetails.controller;

import com.mpm.beforeandafter.contactdetails.dto.CreateContactDetailsRequestDTO;
import com.mpm.beforeandafter.contactdetails.dto.CreateContactDetailsResponseDTO;
import com.mpm.beforeandafter.contactdetails.service.ContactDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/contact_details")
public class ContactDetailsController {

    private final ContactDetailsService contactDetailsService;

    @Autowired
    public ContactDetailsController(ContactDetailsService contactDetailsService) {
        this.contactDetailsService = contactDetailsService;
    }

    @PostMapping()
    public CreateContactDetailsResponseDTO createContactDetails(@RequestBody CreateContactDetailsRequestDTO request){
        return contactDetailsService.createContactDetails(request);
    }

}
