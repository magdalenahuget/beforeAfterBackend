package com.mpm.beforeandafter.image.service;

import com.mpm.beforeandafter.image.dto.*;
import com.mpm.beforeandafter.image.model.Image;

import java.util.List;

public interface ImageService {

    List<GetImagesResponseByStatusApprovalDTO> getImagesByApprovalStatus(GetImagesRequestByStatusApprovalDTO request);

    CreateImageResponseDTO createImage(CreateImageRequestDTO request);

    AddToFavouritesResponseDTO addImageToFavourites(AddToFavouritesRequestDTO request);

    List<Image> getImagesByCategoryAndCity(Long categoryId, String cityName);

    List<GetAllImagesResponseDTO> getAllImages();

    void deleteImage(Long imageId);
}