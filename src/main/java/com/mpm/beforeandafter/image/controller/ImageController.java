package com.mpm.beforeandafter.image.controller;

import com.mpm.beforeandafter.image.dto.*;
import com.mpm.beforeandafter.image.service.ImageService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/images")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    public CreateImageResponseDTO createImage(@Valid @RequestBody CreateImageRequestDTO request) {
        return imageService.createImage(request);
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

    @GetMapping
    public Set<ImageFilterResponseDTO> getAllImagesByDynamicFilter(@RequestBody ImageFilterRequestDTO request){
        return imageService.getImagesByDynamicFilter(request);
    }

    @DeleteMapping("/{id}")
    public void deleteImage(@PathVariable("id") Long imageId) {
        imageService.deleteImage(imageId);
    }
}
