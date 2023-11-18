package com.mpm.beforeandafter.email;

import com.mpm.beforeandafter.contactdetails.dto.GetContactDetailsResponseDto;
import com.mpm.beforeandafter.contactdetails.service.ContactDetailsService;
import com.mpm.beforeandafter.email.dto.ContactFormDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;
    private final ContactDetailsService contactDetailsService;

    @Autowired
    public EmailService(JavaMailSender mailSender, ContactDetailsService contactDetailsService) {
        this.mailSender = mailSender;
        this.contactDetailsService = contactDetailsService;
    }

    @Async
    public void sendContactEmailToServiceProvider(Long userId, ContactFormDto contactForm) {
        GetContactDetailsResponseDto contactDetails =
                contactDetailsService.getContactDetailsByUserId(userId);
        if (contactDetails == null) {
            throw new IllegalArgumentException("No contact details found for user ID: " + userId);
        }
        SimpleMailMessage message = createEmail(
                contactForm.email(),
                contactDetails.getEmail(),
                "New Contact from " + contactForm.name(),
                contactForm.message()
        );

        mailSender.send(message);
    }

    private SimpleMailMessage createEmail(String from, String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        return message;
    }
}