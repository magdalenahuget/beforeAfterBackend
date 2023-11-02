package com.mpm.beforeandafter.image.dto;

import com.mpm.beforeandafter.category.dto.GetCategoryResponse;
import com.mpm.beforeandafter.image.model.Image;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder

public class ImageFilterResponseDTO {
    private long id;
    private String file;
    private String cityName;
    private GetCategoryResponse category;
    private boolean approvalStatus;

    public static Set<ImageFilterResponseDTO> map(Set<Image>images){
        Set<ImageFilterResponseDTO> result = new HashSet<>();

        for (Image image: images) {
            ImageFilterResponseDTO mappedImage = ImageFilterResponseDTO.builder()
                    .id(image.getId())
                    .file(image.getFile())
                    .cityName(image.getCityName())
                    .category(GetCategoryResponse.map(image.getCategory()))
                    .approvalStatus(image.isApproved())
                    .build();
            result.add(mappedImage);
        }
        return result;
    }
}
