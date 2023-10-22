package com.mpm.beforeandafter.service_category.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.awt.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;


    @Column(name = "category_name", nullable = false, unique = true, length = 25)
    @NotBlank(message = "Category name is mandatory.")
    @Size(min=2, max=25,message="Category name must be between 2 and 25 characters long.")
    @NonNull
    private String categoryName;

    @OneToMany(mappedBy = "category", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<Image> images;
}