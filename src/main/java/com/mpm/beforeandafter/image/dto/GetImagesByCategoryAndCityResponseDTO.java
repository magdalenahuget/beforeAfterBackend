package com.mpm.beforeandafter.image.dto;

import com.mpm.beforeandafter.category.dto.GetCategoryResponse;
import com.mpm.beforeandafter.image.model.Image;
import com.mpm.beforeandafter.user.dto.GetUserResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetImagesByCategoryAndCityResponseDTO {

    private Long id;
    private String file;
    private GetCategoryResponse category;
    private String description;
    private String cityName;
    private GetUserResponse user;

    public static GetImagesByCategoryAndCityResponseDTO map(Image image){
        return GetImagesByCategoryAndCityResponseDTO.builder()
                .id(image.getId())
                .file(image.getFile())
                .category(GetCategoryResponse.map(image.getCategory()))
                .description(image.getDescription())
                .cityName(image.getCityName())
                .user(GetUserResponse.map(image.getUser()))
                .build();
    }
}
