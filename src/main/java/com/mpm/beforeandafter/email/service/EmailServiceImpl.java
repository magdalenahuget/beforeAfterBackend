package com.mpm.beforeandafter.email.service;

import com.mpm.beforeandafter.contactdetails.dto.GetContactDetailsResponseDto;
import com.mpm.beforeandafter.contactdetails.service.ContactDetailsService;
import com.mpm.beforeandafter.email.dto.ContactFormRequestDto;
import com.mpm.beforeandafter.email.dto.EmailResponseDto;
import com.mpm.beforeandafter.email.model.EmailLog;
import com.mpm.beforeandafter.email.repository.EmailLogRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final EmailLogRepository emailLogRepository;
    private final ContactDetailsService contactDetailsService;


    @Value("${spring.mail.username}")
    private String beforeAfterMainEmail;

    @Value("${beforeandafter.app.frontendUrl}")
    private String frontendBaseUrl;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender,
                            EmailLogRepository emailLogRepository,
                            ContactDetailsService contactDetailsService) {
        this.javaMailSender = javaMailSender;
        this.emailLogRepository = emailLogRepository;
        this.contactDetailsService = contactDetailsService;
    }

    @Override
    @Async
    public CompletableFuture<EmailResponseDto> sendContactEmail(ContactFormRequestDto contactFormDto) {
        String recipientEmail = determineRecipientEmail(contactFormDto);
        String senderEmail = contactFormDto.senderEmail();
        String subject = "Before & After - Request for an offer ...";
        String content = contactFormDto.emailContent();
        return sendEmail(senderEmail, recipientEmail, subject, content);
    }

    @Override
    @Async
    public CompletableFuture<EmailResponseDto> sendRegistrationEmail(String userName, String userEmail) {
        String subject = "Welcome to Before & After!";
        String content = "Thank you for registering, " + userName + "!";
        return sendEmail(beforeAfterMainEmail, userEmail, subject, content);
    }

    @Async
    public CompletableFuture<EmailResponseDto> sendPasswordResetEmail(String userEmail, String token) {
        String resetLink = frontendBaseUrl + "/reset-password?token=" + token;

        String subject = "Password Reset Request";
        String content = "To reset your password, click the link below:\n" + resetLink;

        return sendEmail(beforeAfterMainEmail, userEmail, subject, content);
    }

    private CompletableFuture<EmailResponseDto> sendEmail(String sender,
                                                          String recipient,
                                                          String subject,
                                                          String content) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                MimeMessage mimeMessage = createMimeMessage(sender, recipient, subject, content);
                javaMailSender.send(mimeMessage);
                logEmail(recipient, subject, LocalDateTime.now(), true, null);
                return new EmailResponseDto(true);
            } catch (MessagingException e) {
                logEmail(recipient, subject, LocalDateTime.now(), false, e.getMessage());
                return new EmailResponseDto(false);
            }
        });
    }

    private MimeMessage createMimeMessage(String sender, String recipient, String subject, String content)
            throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
        message.setFrom(sender);
        message.setReplyTo(sender);
        message.setTo(recipient);
        message.setSubject(subject);
        message.setText(content, true); // HTML content - stworzyć ?
        return mimeMessage;
    }

    private String determineRecipientEmail(ContactFormRequestDto contactFormDto) {
        String recipientEmail = beforeAfterMainEmail;
        if (contactFormDto.offerUserId() != null) {
            GetContactDetailsResponseDto contactDetails = getContactDetailsOrThrow(contactFormDto.offerUserId());
            recipientEmail = contactDetails.getEmail();
        }
        return recipientEmail;
    }

    private GetContactDetailsResponseDto getContactDetailsOrThrow(Long userId) {
        return Optional.ofNullable(contactDetailsService.getContactDetailsByUserId(userId))
                .orElseThrow(() -> new IllegalArgumentException("No contact details found for user ID: " + userId));
    }

    private void logEmail(String to, String subject, LocalDateTime dateSent, Boolean success, String errorMessage) {
        EmailLog log = new EmailLog();
        log.setToAddress(to);
        log.setSubject(subject);
        log.setDateSent(dateSent);
        log.setSuccess(success);
        log.setErrorMessage(errorMessage);
        emailLogRepository.save(log);
    }
}