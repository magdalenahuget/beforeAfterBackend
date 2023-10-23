package com.mpm.beforeandafter.image.model;

import com.mpm.beforeandafter.status.type.StatusesType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="image_id")
    private Long id;

    @Column(name="image")
    private String image;

    @Column(name="service_category_id")
    private Long serviceCategoryId;

    @Column(name="image_description")
    private String description;

    @Column(name="user_id")
    private Long userId;

    @Column(name="image_approved")
    private boolean isApproved;

    @Column(name="image_approved_by")
    private String approvedBy;

    @Column(name="image_approved_date")
    private LocalDate approvedDate;

    @Column(name="status")
    private StatusesType status;
}
