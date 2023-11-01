package com.mpm.beforeandafter.favourites.service;


import com.mpm.beforeandafter.favourites.dto.AddToFavouritesResponseDTO;
import com.mpm.beforeandafter.favourites.dto.DeleteFavouriteResponseDTO;
import org.springframework.stereotype.Component;

@Component
class FavouritesMapper {

    AddToFavouritesResponseDTO mapToAddedToFavouritesDTO(Long userId, Long imageId,
                                                         String status) {
        return AddToFavouritesResponseDTO.builder()
                .userId(userId)
                .imageId(imageId)
                .status(status)
                .build();
    }

    DeleteFavouriteResponseDTO mapToDeleteFavouriteDTO(String message) {
        return DeleteFavouriteResponseDTO.builder()
                .message(message)
                .build();
    }
}