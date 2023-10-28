package com.mpm.beforeandafter.image.dto;

import com.mpm.beforeandafter.image.model.Image;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class GetAllImagesResponseDTO {
    private Long id;
    private String file;
    private String description;
    private Long categoryId;
    private Long userId;

    public static List<GetAllImagesResponseDTO> map(List<Image> images) {
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

}
