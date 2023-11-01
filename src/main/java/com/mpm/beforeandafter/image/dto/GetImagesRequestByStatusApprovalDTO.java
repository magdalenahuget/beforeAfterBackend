package com.mpm.beforeandafter.image.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetImagesRequestByStatusApprovalDTO {
    private boolean approvalStatus;

}
