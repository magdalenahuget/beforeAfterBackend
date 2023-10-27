package com.mpm.beforeandafter.image.service;

import com.mpm.beforeandafter.image.dto.CreateImageRequest;
import com.mpm.beforeandafter.image.dto.GetImagesRequestByStatusApproval;
import com.mpm.beforeandafter.image.model.Image;

import java.util.List;

public interface ImageService {

    List<Image> getImagesByApprovalStatus(GetImagesRequestByStatusApproval request);

    Image createImage(CreateImageRequest request);

//    List<Image> getImagesByCategoryAndCity(Long categoryId, String cityName);
}