package com.mpm.beforeandafter.image.service;

import com.mpm.beforeandafter.category.service.CategoryMapper;
import com.mpm.beforeandafter.image.dto.CreateImageResponseDTO;
import com.mpm.beforeandafter.image.dto.GetImagesByCategoryAndCityResponseDTO;
import com.mpm.beforeandafter.image.model.Image;
import com.mpm.beforeandafter.user.service.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class ImageMapper {

    private CategoryMapper categoryMapper;
    private UserMapper userMapper;

    @Autowired
    ImageMapper(CategoryMapper categoryMapper, UserMapper userMapper) {
        this.categoryMapper = categoryMapper;
        this.userMapper = userMapper;
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

    GetImagesByCategoryAndCityResponseDTO mapToGetImagesByCategoryAndCityDTO(
            Image image) {
        return GetImagesByCategoryAndCityResponseDTO.builder()
                .id(image.getId())
                .file(image.getFile())
                .category(categoryMapper.mapToCategoryResponseDto(image.getCategory()))
                .description(image.getDescription())
                .cityName(image.getCityName())
                .user(userMapper.mapToGetUserResponseDto(image.getUser()))
                .build();
    }
}