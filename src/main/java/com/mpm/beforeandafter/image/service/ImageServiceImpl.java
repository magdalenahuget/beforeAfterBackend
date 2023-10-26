package com.mpm.beforeandafter.image.service;

import com.mpm.beforeandafter.category.repository.CategoryRepository;
import com.mpm.beforeandafter.image.dto.CreateImageRequest;
import com.mpm.beforeandafter.image.dto.GetImagesRequestByStatusApproval;
import com.mpm.beforeandafter.image.model.Image;
import com.mpm.beforeandafter.image.repository.ImageRepository;
import com.mpm.beforeandafter.user.model.StatusType;
import com.mpm.beforeandafter.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.imageRepository = imageRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Image> getImagesByApprovalStatus(GetImagesRequestByStatusApproval request) {
        boolean approvalStatus = request.isApprovalStatus();
        return imageRepository.findAll().stream()
                .toList()
                .stream()
                .filter(image -> image.isApproved() == approvalStatus)
                .toList();
    }

    @Override
    public Image createImage(CreateImageRequest request) {
        Image image = new Image();
        image.setFile(request.getFile());
        image.setCategory(categoryRepository.getReferenceById(request.getCategoryId()));
        image.setDescription(request.getDescription());
        image.setUser(userRepository.getReferenceById(request.getUserId()));
        image.setStatus(StatusType.TO_REVIEW);
        log.info(image.toString());

        return imageRepository.save(image);
    }
}
