package com.mpm.beforeandafter.image.controller;

import com.mpm.beforeandafter.image.dto.CreateImageRequest;
import com.mpm.beforeandafter.image.dto.CreateImageResponse;
import com.mpm.beforeandafter.image.dto.GetImagesRequestByStatusApproval;
import com.mpm.beforeandafter.image.dto.GetImagesResponseByStatusApproval;
import com.mpm.beforeandafter.image.model.Image;
import com.mpm.beforeandafter.image.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    public List<GetImagesResponseByStatusApproval> getImages(@RequestBody GetImagesRequestByStatusApproval request){
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
}
