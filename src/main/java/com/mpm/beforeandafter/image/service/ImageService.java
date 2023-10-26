package com.mpm.beforeandafter.image.service;

import com.mpm.beforeandafter.image.dto.CreateImageRequest;
import com.mpm.beforeandafter.image.model.Image;

import java.util.List;

public interface ImageService {
    List<Image> getAllImagesByApproved(boolean isApproved);
    Image createImage(CreateImageRequest request);



}
