package com.mpm.beforeandafter.user.model;


import com.mpm.beforeandafter.image.model.Image;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor

@Entity
@Table(name = "favourites")
public class Favourite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favourite_id")
    private Long favouriteId;

    @NotNull
    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NonNull
    @OneToMany(mappedBy = "favourite")
    private Set<Image> images;
}