package com.mpm.beforeandafter.image.controller;

import com.mpm.beforeandafter.image.dto.*;
import com.mpm.beforeandafter.image.service.ImageService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/images")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public List<GetImagesResponseByStatusApprovalDTO> getImages(
            @Valid @RequestBody GetImagesRequestByStatusApprovalDTO request) {
        return imageService.getImagesByApprovalStatus(request);
    }

    @PostMapping
    public CreateImageResponseDTO createImage(@Valid @RequestBody CreateImageRequestDTO request) {
        return imageService.createImage(request);
    }

    /**
     * TODO: usunięcie userId ze ścieżki - implementacja wyciągania userId z sesji lub tokena jwt.
     *
     * SKONSULTOWAĆ: przekazywanie imageId w kontekście JWT? Które podejście PathVariable/DTO?
     */
    @PostMapping("/{imageId}/users/{userId}/favourites")
    public AddToFavouritesResponseDTO addImageToFavourites(
            @PathVariable Long imageId, @PathVariable Long userId) {
        return imageService.addImageToFavourites(imageId, userId);
    }


//    @GetMapping("/categories/{id}/city")
//    public List<GetImagesByCategoryAndCityResponse> getImagesByCategoryAndCity(
//            @PathVariable("id") Long categoryId,
//            @RequestParam(name = "cityName", required = false) String cityName) {
//        log.debug("Get all images by category: {} and city name: {}", categoryId, cityName);
//        List<Image> images = imageService.getImagesByCategoryAndCity(categoryId, cityName);
//        log.info("Images by category and city: {}", images);
//        List<GetImagesByCategoryAndCityResponse> imagesByCategoryAndCity = new ArrayList<>();
//        for (Image image : images) {
//            GetImagesByCategoryAndCityResponse imageByCategoryAndCity = GetImagesByCategoryAndCityResponse.map(image);
//            imagesByCategoryAndCity.add(imageByCategoryAndCity);
//        }
//        return imagesByCategoryAndCity;
//    }

    @GetMapping("/all")
    public List<GetAllImagesResponseDTO> getAllImages() {
        return imageService.getAllImages();
    }

    @DeleteMapping("/{id}")
    public void deleteImage(@PathVariable("id") Long imageId) {
        imageService.deleteImage(imageId);
    }
}
