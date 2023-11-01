package com.mpm.beforeandafter.image.dto;

import com.mpm.beforeandafter.user.model.StatusType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetImagesResponseByStatusApprovalDTO {
    private long id;
    private String file;
    private StatusType status;

}



