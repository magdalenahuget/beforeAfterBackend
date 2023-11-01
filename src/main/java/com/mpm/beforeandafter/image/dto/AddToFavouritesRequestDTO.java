package com.mpm.beforeandafter.image.dto;

import jakarta.validation.constraints.NotNull;

public record AddToFavouritesRequestDTO(
        @NotNull(message = "Image ID is mandatory") Long imageId,
        @NotNull(message = "User ID is mandatory") Long userId) {
}