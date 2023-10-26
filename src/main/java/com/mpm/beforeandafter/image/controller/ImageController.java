package com.mpm.beforeandafter.image.controller;

import com.mpm.beforeandafter.image.dto.*;
import com.mpm.beforeandafter.image.model.Image;
import com.mpm.beforeandafter.image.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.mpm.beforeandafter.image.dto.CreateImageResponse.map;
import static com.mpm.beforeandafter.image.dto.GetImagesResponseByStatusApproval.mapByStatusApproval;

@Slf4j
@RestController
@RequestMapping("/api/v1/images")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public List<GetImagesResponseByStatusApproval> getImages(@RequestBody GetImagesRequestByStatusApproval request) {
        log.debug("Get all images by approval status: {}", request);
        List<Image> images = imageService.getImagesByApprovalStatus(request);
        log.info("Images by status approval: {}", images);
        return mapByStatusApproval(images);
    }

    @PostMapping
    public CreateImageResponse createImage(@RequestBody CreateImageRequest request) {
        log.debug("Creating category: {}", request);
        Image savedImage = imageService.createImage(request);
        log.info("Category created: {}", savedImage);
        return map(savedImage);
    }

    @GetMapping("/categories/{id}/city")
    public List<GetImagesByCategoryAndCityResponse> getImagesByCategoryAndCity(
            @PathVariable("id") Long categoryId,
            @RequestParam(name = "cityName", required = false) String cityName) {
        log.debug("Get all images by category: {} and city name: {}", categoryId, cityName);
        List<Image> images = imageService.getImagesByCategoryAndCity(categoryId, cityName);
        log.info("Images by category and city: {}", images);
        List<GetImagesByCategoryAndCityResponse> imagesByCategoryAndCity = new ArrayList<>();
        for (Image image : images) {
            GetImagesByCategoryAndCityResponse imageByCategoryAndCity = GetImagesByCategoryAndCityResponse.map(image);
            imagesByCategoryAndCity.add(imageByCategoryAndCity);
        }
        return imagesByCategoryAndCity;
    }
}
