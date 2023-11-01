package com.mpm.beforeandafter.favourites.controller;

import com.mpm.beforeandafter.favourites.dto.AddToFavouritesResponseDTO;
import com.mpm.beforeandafter.favourites.dto.DeleteFavouriteResponseDTO;
import com.mpm.beforeandafter.favourites.service.FavouritesService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/favourites")
@CrossOrigin(origins = "http://localhost:3000")
public class FavouritesController {

    private final FavouritesService favouritesService;

    public FavouritesController(FavouritesService favouritesService) {
        this.favouritesService = favouritesService;
    }

    //TO DO : usunięcie userId ze ścieżki - implementacja wyciągania userId z sesji lub tokena jwt.
    //SKONSULTOWAĆ: przekazywanie imageId w kontekście JWT? Które podejście PathVariable/DTO?
    @PostMapping("/images/{imageId}/users/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public AddToFavouritesResponseDTO addImageToFavourites(
            @PathVariable Long imageId, @PathVariable Long userId) {
        return favouritesService.addImageToFavourites(imageId, userId);
    }

    @DeleteMapping("images/{imageId}/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public DeleteFavouriteResponseDTO deleteFavourite(
            @PathVariable Long imageId, @PathVariable Long userId) {
        return favouritesService.deleteFavourite(imageId, userId);
    }
}