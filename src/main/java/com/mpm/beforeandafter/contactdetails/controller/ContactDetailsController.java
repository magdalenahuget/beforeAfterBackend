package com.mpm.beforeandafter.contactdetails.controller;

import com.mpm.beforeandafter.contactdetails.dto.CreateContactDetailsRequestDTO;
import com.mpm.beforeandafter.contactdetails.dto.CreateContactDetailsResponseDTO;
import com.mpm.beforeandafter.contactdetails.dto.GetContactDetailsResponseDTO;
import com.mpm.beforeandafter.contactdetails.service.ContactDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/contact_details")
@CrossOrigin(origins = "http://localhost:3000")
public class ContactDetailsController {

    private final ContactDetailsService contactDetailsService;

    @Autowired
    public ContactDetailsController(ContactDetailsService contactDetailsService) {
        this.contactDetailsService = contactDetailsService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CreateContactDetailsResponseDTO createContactDetails(@RequestBody CreateContactDetailsRequestDTO request) {
        return contactDetailsService.createContactDetails(request);
    }

    @GetMapping("/users/{id}")
    public GetContactDetailsResponseDTO getContactDetailsByUserId(@PathVariable("id") Long userId) {
        return contactDetailsService.getContactDetailsByUserId(userId);
    }
}
