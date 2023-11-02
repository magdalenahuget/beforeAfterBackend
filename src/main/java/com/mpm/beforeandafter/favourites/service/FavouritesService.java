package com.mpm.beforeandafter.favourites.service;

import com.mpm.beforeandafter.favourites.dto.AddToFavouritesResponseDto;
import com.mpm.beforeandafter.favourites.dto.DeleteFavouriteResponseDto;

public interface FavouritesService {

    AddToFavouritesResponseDto addImageToFavourites(Long imageId, Long userId);

    DeleteFavouriteResponseDto deleteFavourite(Long imageId, Long userId);
}
