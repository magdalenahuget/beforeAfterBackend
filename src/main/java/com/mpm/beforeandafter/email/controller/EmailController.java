package com.mpm.beforeandafter.email.controller;

import com.mpm.beforeandafter.email.EmailService;
import com.mpm.beforeandafter.email.dto.ContactFormDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/emails")
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    //zmiana nazewnictwa?
    @PostMapping("/contact-offer/users/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendContactOfferEmail(@PathVariable Long userId,
                                      @RequestBody ContactFormDto contactForm) {
        emailService.sendContactEmailToServiceProvider(userId, contactForm);
    }
}