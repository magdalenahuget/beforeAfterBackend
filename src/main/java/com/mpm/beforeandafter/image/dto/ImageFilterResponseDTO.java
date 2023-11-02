package com.mpm.beforeandafter.image.dto;

import com.mpm.beforeandafter.category.dto.CategoryResponseDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class ImageFilterResponseDTO {
    private long id;
    private String file;
    private String cityName;
    private CategoryResponseDto category;
    private boolean approvalStatus;
}
