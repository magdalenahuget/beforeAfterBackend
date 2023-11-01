package com.mpm.beforeandafter.favourites.controller;

import com.mpm.beforeandafter.favourites.dto.DeleteFavouriteResponseDTO;
import com.mpm.beforeandafter.favourites.service.FavouritesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/favourites")
public class FavouritesController {

    private final FavouritesService favouritesService;

    public FavouritesController(FavouritesService favouritesService) {
        this.favouritesService = favouritesService;
    }

    @DeleteMapping("images/{imageId}/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public DeleteFavouriteResponseDTO deleteFavourite(
            @PathVariable Long imageId, @PathVariable Long userId) {
        return favouritesService.deleteFavourite(imageId, userId);
    }
}
