package com.mpm.beforeandafter.image.dto;

import com.mpm.beforeandafter.category.dto.GetCategoryResponse;
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
}
