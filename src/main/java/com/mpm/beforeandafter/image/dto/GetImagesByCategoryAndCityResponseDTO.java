package com.mpm.beforeandafter.image.dto;

import com.mpm.beforeandafter.category.dto.CategoryResponse;
import com.mpm.beforeandafter.image.model.Image;
import com.mpm.beforeandafter.user.dto.GetUserResponseDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetImagesByCategoryAndCityResponseDTO {

    private Long id;
    private String file;
    private CategoryResponse category;
    private String description;
    private String cityName;
    private GetUserResponseDto user;

    public static GetImagesByCategoryAndCityResponseDTO map(Image image){
        return GetImagesByCategoryAndCityResponseDTO.builder()
                .id(image.getId())
                .file(image.getFile())
                .category(CategoryResponse.map(image.getCategory()))
                .description(image.getDescription())
                .cityName(image.getCityName())
                .user(GetUserResponseDto.map(image.getUser()))
                .build();
    }
}
