package com.mpm.beforeandafter.favourites.service;

import com.mpm.beforeandafter.favourites.dto.DeleteFavouriteResponseDTO;

public interface FavouritesService {

    DeleteFavouriteResponseDTO deleteFavourite(Long imageId, Long userId);
}
