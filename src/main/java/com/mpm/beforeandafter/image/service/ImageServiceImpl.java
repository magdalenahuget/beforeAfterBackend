package com.mpm.beforeandafter.image.service;

import com.mpm.beforeandafter.category.model.Category;
import com.mpm.beforeandafter.category.repository.CategoryRepository;
import com.mpm.beforeandafter.exception.ResourceNotFoundException;
import com.mpm.beforeandafter.image.dto.*;
import com.mpm.beforeandafter.image.model.Image;
import com.mpm.beforeandafter.image.repository.ImageRepository;
import com.mpm.beforeandafter.user.model.StatusType;
import com.mpm.beforeandafter.user.model.User;
import com.mpm.beforeandafter.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {
    private static final String IMAGE_NOT_FOUND_LOG_ERROR_MSG =
            "There is no image found with the given ID: {}";
    private static final String IMAGE_NOT_FOUND_EXCEPTION_MSG =
            "There is no image found with the given ID: ";

    private static final String USER_NOT_FOUND_LOG_ERROR_MSG =
            "There is no user with the given ID: {}";
    private static final String USER_NOT_FOUND_EXCEPTION_MSG =
            "There is no user with the given ID: ";

    private final ImageRepository imageRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository, CategoryRepository categoryRepository,
                            UserRepository userRepository) {
        this.imageRepository = imageRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<GetImagesResponseByStatusApprovalDTO> getImagesByApprovalStatus(
            GetImagesRequestByStatusApprovalDTO request) {
        log.debug("Get all images by approval status: {}", request);
        List<Image> allImagesByApprovalStatus = new ArrayList<>();
        boolean approvalStatus = request.isApprovalStatus();
        allImagesByApprovalStatus = imageRepository.findAll().stream()
                .toList()
                .stream()
                .filter(image -> image.isApproved() == approvalStatus)
                .toList();
        log.info("Images by status approval: {}", allImagesByApprovalStatus);

        return GetImagesResponseByStatusApprovalDTO.map(allImagesByApprovalStatus);
    }

    @Override
    public CreateImageResponseDTO createImage(CreateImageRequestDTO request) {
        log.debug("Creating new Image: {}", request);
        Image image = new Image();
        image.setFile(request.getFile());
        image.setCategory(categoryRepository.getReferenceById(request.getCategoryId()));
        image.setDescription(request.getDescription());
        image.setUser(userRepository.getReferenceById(request.getUserId()));
        image.setStatus(StatusType.TO_REVIEW);
        imageRepository.save(image);
        log.info("New Image created: {}", image);

        return CreateImageResponseDTO.map(image);
    }


    @Override
    public AddToFavouritesResponseDTO addImageToFavourites(Long imageId, Long userId) {
        log.debug("Adding image with ID: {} to the favourites of user with ID: {}", imageId,
                userId);


        Image image = imageRepository.findById(imageId)
                .orElseThrow(() -> {
                    log.error(IMAGE_NOT_FOUND_LOG_ERROR_MSG, imageId);
                    return new ResourceNotFoundException(IMAGE_NOT_FOUND_EXCEPTION_MSG + imageId);
                });

        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.error(USER_NOT_FOUND_LOG_ERROR_MSG, userId);
                    return new ResourceNotFoundException(USER_NOT_FOUND_EXCEPTION_MSG + userId);
                });

        if (image.getUsers().contains(user)) {
            log.info("Image with ID: {} is already in the favourites of user with ID: {}", imageId,
                    userId);
            return new AddToFavouritesResponseDTO(userId, imageId,
                    "Image is already in favourites");
        }

        image.getUsers().add(user);
        imageRepository.save(image);

        log.info("Image with ID: {} added to the favourites of user with ID: {}", imageId, userId);
        return new AddToFavouritesResponseDTO(userId, imageId, "Image added to favourites");
    }


    @Override
    public List<Image> getImagesByCategoryAndCity(Long categoryId, String cityName) {
        Category category = categoryRepository.getReferenceById(categoryId);
        return imageRepository.findImagesByCategoryAndCityName(category, cityName);
    }

    @Override
    public List<GetAllImagesResponseDTO> getAllImages() {
        log.debug("Get all images");
        List<Image> images = imageRepository.findAll();
        log.info("All images: {}", images);
        return GetAllImagesResponseDTO.map(images);
    }

    @Override
    public void deleteImage(Long imageId) {
        log.debug("Deleting image with id: {}", imageId);

        imageRepository
                .findById(imageId)
                .ifPresentOrElse(image -> {
                    imageRepository.delete(image);
                    log.info("Category with id: {} deleted successfully", imageId);
                }, () -> {
                    log.error(IMAGE_NOT_FOUND_LOG_ERROR_MSG, imageId);
                    throw new ResourceNotFoundException((IMAGE_NOT_FOUND_EXCEPTION_MSG + imageId));
                });
    }
}