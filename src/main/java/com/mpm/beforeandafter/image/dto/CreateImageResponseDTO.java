package com.mpm.beforeandafter.image.dto;

import com.mpm.beforeandafter.image.model.Image;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateImageResponseDTO {
    private long imageId;
    private String file;
    private Long categoryId;
    private String description;
    private Long userId;

    public static CreateImageResponseDTO map(Image image) {
        return CreateImageResponseDTO.builder()
                .imageId(image.getId())
                .file(image.getFile())
                .categoryId(image.getCategory().getId())
                .description(image.getDescription())
                .userId(image.getUser().getId())
                .build();
    }
}
