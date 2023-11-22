package com.mpm.beforeandafter.contactdetails.controller;

import com.mpm.beforeandafter.contactdetails.dto.ContactDetailsRequestDto;
import com.mpm.beforeandafter.contactdetails.dto.ContactDetailsResponseDto;
import com.mpm.beforeandafter.contactdetails.dto.GetContactDetailsResponseDto;
import com.mpm.beforeandafter.contactdetails.service.ContactDetailsService;
import com.mpm.beforeandafter.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/contact_details")
public class ContactDetailsController {

    private final ContactDetailsService contactDetailsService;

    @Autowired
    public ContactDetailsController(ContactDetailsService contactDetailsService) {
        this.contactDetailsService = contactDetailsService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDetailsResponseDto createContactDetails(@RequestBody ContactDetailsRequestDto request) {
        return contactDetailsService.createContactDetails(request);
    }

    @GetMapping("/users/{id}")
    public GetContactDetailsResponseDto getContactDetailsByUserId(@PathVariable("id") Long userId) {
        GetContactDetailsResponseDto contactDetailsByUserId;
        try {
            contactDetailsByUserId = contactDetailsService.getContactDetailsByUserId(userId);
        } catch (ResourceNotFoundException exception) {
            contactDetailsByUserId = contactDetailsService.createAndGetDefaultContactDetails(userId);
        }
        return contactDetailsByUserId;
    }

    @PatchMapping("users/{id}")
    public ContactDetailsResponseDto updateContactDetailsByUserId(@PathVariable("id") Long userId,
                                                  @Valid @RequestBody ContactDetailsRequestDto request) {
        return contactDetailsService.modifiedContactDetails(userId,request);
    }
}
