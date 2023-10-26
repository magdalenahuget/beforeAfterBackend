package com.mpm.beforeandafter.image.controller;

import com.mpm.beforeandafter.image.dto.CreateImageRequest;
import com.mpm.beforeandafter.image.dto.CreateImageResponse;
import com.mpm.beforeandafter.image.model.Image;
import com.mpm.beforeandafter.image.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.mpm.beforeandafter.image.dto.CreateImageResponse.map;

@Slf4j
@RestController
@RequestMapping("/api/images")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    public CreateImageResponse createImage(@RequestBody CreateImageRequest request) {
        log.debug("Creating category: {}", request);
        Image savedImage = imageService.createImage(request);
        log.info("Category created: {}", savedImage);
        return map(savedImage);
    }
}
