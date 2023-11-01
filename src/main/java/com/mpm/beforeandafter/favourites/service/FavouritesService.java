package com.mpm.beforeandafter.favourites.service;

import com.mpm.beforeandafter.favourites.dto.AddToFavouritesResponseDTO;
import com.mpm.beforeandafter.favourites.dto.DeleteFavouriteResponseDTO;

public interface FavouritesService {

    AddToFavouritesResponseDTO addImageToFavourites(Long imageId, Long userId);

    DeleteFavouriteResponseDTO deleteFavourite(Long imageId, Long userId);
}
