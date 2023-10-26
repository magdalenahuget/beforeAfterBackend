package com.mpm.beforeandafter.image.dto;

import com.mpm.beforeandafter.image.model.Image;
import com.mpm.beforeandafter.user.model.StatusType;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class GetImagesResponseByStatusApproval {
    private long id;
    private String file;
    private StatusType status;

    public static List<GetImagesResponseByStatusApproval> mapByStatusApproval(List<Image> images) {
        List<GetImagesResponseByStatusApproval> result = new ArrayList<>();

        for (Image image : images) {
            GetImagesResponseByStatusApproval mappedImage = GetImagesResponseByStatusApproval.builder()
                    .id(image.getId())
                    .file(image.getFile())
                    .status(image.getStatus())
                    .build();
            result.add(mappedImage);

        }
        return result;
    }


}



