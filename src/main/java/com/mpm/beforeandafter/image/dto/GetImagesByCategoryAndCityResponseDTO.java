package com.mpm.beforeandafter.image.dto;

import com.mpm.beforeandafter.category.dto.CategoryResponseDTO;
import com.mpm.beforeandafter.user.dto.GetUserResponseDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetImagesByCategoryAndCityResponseDTO {

    private Long id;
    private String file;
    private CategoryResponseDTO category;
    private String description;
    private String cityName;
    private GetUserResponseDto user;
}
