package com.mpm.beforeandafter.image.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CreateImageRequest {

    @NotNull(message = "File is mandatory")
    private String file;

    @NotNull(message = "CategoryId is mandatory")
    private Long categoryId;

    @NotNull(message = "Description is mandatory")
    private String description;

    @NotNull(message = "UserId is mandatory")
    private Long userId;
}
