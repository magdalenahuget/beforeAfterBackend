package com.mpm.beforeandafter.image.model;

import com.mpm.beforeandafter.service_category.model.Category;
import com.mpm.beforeandafter.status.type.StatusesType;
import com.mpm.beforeandafter.user.model.User;
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
    @Column(name = "image_id")
    private Long id;

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "service_category_id", nullable = false)
    private Category category;

    @Column(name = "image_description")
    private String description;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

    @Column(name = "image_approved")
    private boolean isApproved;

    @Column(name = "image_approved_by")
    private String approvedBy;

    @Column(name = "image_approved_date")
    private LocalDate approvedDate;

    @Column(name = "status")
    private StatusesType status;
}
