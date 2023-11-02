package com.mpm.beforeandafter.image.service;

import com.mpm.beforeandafter.image.dto.*;
import com.mpm.beforeandafter.image.model.Image;

import java.util.List;
import java.util.Set;

public interface ImageService {

    CreateImageResponseDTO createImage(CreateImageRequestDTO request);

    List<Image> getImagesByCategoryAndCity(Long categoryId, String cityName);

    Set<ImageFilterResponseDTO> getImagesByDynamicFilter(ImageFilterRequestDTO request);


    void deleteImage(Long imageId);
}