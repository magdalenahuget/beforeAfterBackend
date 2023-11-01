package com.mpm.beforeandafter.image.service;


import com.mpm.beforeandafter.category.dto.GetCategoryResponse;
import com.mpm.beforeandafter.image.dto.CreateImageResponseDTO;
import com.mpm.beforeandafter.image.dto.GetAllImagesResponseDTO;
import com.mpm.beforeandafter.image.dto.GetImagesByCategoryAndCityResponseDTO;
import com.mpm.beforeandafter.image.dto.GetImagesResponseByStatusApprovalDTO;
import com.mpm.beforeandafter.image.model.Image;
import com.mpm.beforeandafter.user.dto.GetUserResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class ImageMapper {
    CreateImageResponseDTO mapToCreateImageDTO(Image image) {
        return CreateImageResponseDTO.builder()
                .imageId(image.getId())
                .file(image.getFile())
                .categoryId(image.getCategory().getId())
                .description(image.getDescription())
                .userId(image.getUser().getId())
                .build();
    }

    List<GetAllImagesResponseDTO> mapToGetAllImagesDTO(List<Image> images) {
        List<GetAllImagesResponseDTO> result = new ArrayList<>();

        for (Image image : images) {
            GetAllImagesResponseDTO mappedImage = GetAllImagesResponseDTO.builder()
                    .id(image.getId())
                    .file(image.getFile())
                    .description(image.getDescription())
                    .categoryId(image.getCategory().getId())
                    .userId(image.getUser().getId())
                    .build();
            result.add(mappedImage);
        }
        return result;
    }

    GetImagesByCategoryAndCityResponseDTO mapToGetImagesByCategoryAndCityDTO(
            Image image) {
        return GetImagesByCategoryAndCityResponseDTO.builder()
                .id(image.getId())
                .file(image.getFile())
                .category(GetCategoryResponse.map(image.getCategory()))
                .description(image.getDescription())
                .cityName(image.getCityName())
                .user(GetUserResponse.map(image.getUser()))
                .build();
    }

    List<GetImagesResponseByStatusApprovalDTO> mapToGetImagesByStatusApprovalDTO(
            List<Image> images) {
        List<GetImagesResponseByStatusApprovalDTO> result = new ArrayList<>();
        for (Image image : images) {
            GetImagesResponseByStatusApprovalDTO mappedImage =
                    GetImagesResponseByStatusApprovalDTO.builder()
                            .id(image.getId())
                            .file(image.getFile())
                            .status(image.getStatus())
                            .build();
            result.add(mappedImage);
        }
        return result;
    }
}