package com.mpm.beforeandafter.image.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetAllImagesResponseDTO {
    private Long id;
    private String file;
    private String description;
    private Long categoryId;
    private Long userId;
}
