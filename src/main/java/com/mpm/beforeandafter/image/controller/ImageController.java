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

    @GetMapping
    public Set<ImageFilterResponseDTO> getAllImagesByDynamicFilter(@RequestBody ImageFilterRequestDTO request){
        return imageService.getImagesByDynamicFilter(request);
    }

    @DeleteMapping("/{id}")
    public void deleteImage(@PathVariable("id") Long imageId) {
        imageService.deleteImage(imageId);
    }
}
