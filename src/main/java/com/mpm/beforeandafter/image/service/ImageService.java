package com.mpm.beforeandafter.image.service;

import com.mpm.beforeandafter.image.dto.CreateImageRequestDTO;
import com.mpm.beforeandafter.image.dto.CreateImageResponseDTO;
import com.mpm.beforeandafter.image.dto.ImageFilterRequestDTO;
import com.mpm.beforeandafter.image.dto.ImageFilterResponseDTO;

import java.util.Set;

public interface ImageService {

    CreateImageResponseDTO createImage(CreateImageRequestDTO request);
    Set<ImageFilterResponseDTO> getImagesByDynamicFilter(ImageFilterRequestDTO request);
    void deleteImage(Long imageId);
}