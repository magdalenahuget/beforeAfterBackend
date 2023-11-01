package com.mpm.beforeandafter.favourites.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteFavouriteResponseDTO {

    public String message;

    public DeleteFavouriteResponseDTO(String message) {
        this.message = message;
    }
}
