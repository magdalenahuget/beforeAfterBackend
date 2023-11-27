package com.mpm.beforeandafter.email.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "emails")
public class EmailLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fromAddress;
    private String toAddress;
    private String subject;
    private String content;
    private LocalDateTime dateSent;
    private boolean success;
    private String responseStatus;
    private String errorMessage;
}