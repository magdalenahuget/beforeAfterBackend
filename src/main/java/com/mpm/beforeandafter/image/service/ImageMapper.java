package com.mpm.beforeandafter.image.service;

import com.mpm.beforeandafter.category.service.CategoryMapper;
import com.mpm.beforeandafter.image.dto.CreateImageResponseDTO;
import com.mpm.beforeandafter.image.dto.ImageFilterResponseDTO;
import com.mpm.beforeandafter.image.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ImageMapper {
    private final CategoryMapper categoryMapper;

    @Autowired
    public ImageMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    CreateImageResponseDTO mapToCreateImageDTO(Image image) {
        return CreateImageResponseDTO.builder()
                .imageId(image.getId())
                .file(image.getFile())
                .categoryId(image.getCategory().getId())
                .description(image.getDescription())
                .userId(image.getUser().getId())
                .build();
    }

    Set<ImageFilterResponseDTO> mapGetImageByFilter(Set<Image> images) {
        Set<ImageFilterResponseDTO> result = new HashSet<>();

        for (Image image : images) {
            ImageFilterResponseDTO mappedImage = ImageFilterResponseDTO.builder()
                    .id(image.getId())
                    .file(image.getFile())
                    .cityName(image.getCityName())
                    .category(categoryMapper.mapToCategoryResponseDto(image.getCategory()))
                    .approvalStatus(image.isApproved())
                    .build();
            result.add(mappedImage);
        }
        return result;
    }
}
