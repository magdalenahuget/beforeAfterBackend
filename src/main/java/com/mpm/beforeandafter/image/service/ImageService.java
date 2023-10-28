package com.mpm.beforeandafter.image.service;

import com.mpm.beforeandafter.image.dto.CreateImageRequestDTO;
import com.mpm.beforeandafter.image.dto.GetAllImagesResponseDTO;
import com.mpm.beforeandafter.image.dto.GetImagesRequestByStatusApprovalDTO;
import com.mpm.beforeandafter.image.model.Image;

import java.util.List;

public interface ImageService {

    List<Image> getImagesByApprovalStatus(GetImagesRequestByStatusApprovalDTO request);

    Image createImage(CreateImageRequestDTO request);

    List<Image> getImagesByCategoryAndCity(Long categoryId, String cityName);

    List<GetAllImagesResponseDTO> getAllImages();

}