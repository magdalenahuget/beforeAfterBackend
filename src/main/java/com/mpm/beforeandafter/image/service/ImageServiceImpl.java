package com.mpm.beforeandafter.image.service;

import com.mpm.beforeandafter.category.repository.CategoryRepository;
import com.mpm.beforeandafter.exception.ResourceNotFoundException;
import com.mpm.beforeandafter.image.dto.CreateImageRequestDTO;
import com.mpm.beforeandafter.image.dto.CreateImageResponseDTO;
import com.mpm.beforeandafter.image.dto.ImageFilterRequestDTO;
import com.mpm.beforeandafter.image.dto.ImageFilterResponseDTO;
import com.mpm.beforeandafter.image.model.Image;
import com.mpm.beforeandafter.image.repository.ImageRepository;
import com.mpm.beforeandafter.user.model.StatusType;
import com.mpm.beforeandafter.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final ImageMapper imageMapper;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository, CategoryRepository categoryRepository,
                            UserRepository userRepository, ImageMapper imageMapper) {
        this.imageRepository = imageRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.imageMapper = imageMapper;
    }

    @Override
    public Set<ImageFilterResponseDTO> getImagesByDynamicFilter(ImageFilterRequestDTO request) {
        log.info("[OPERATION] Filtering images by criteria...");
        Set<String> validCategories =
                request.getCategories() != null ? request.getCategories() : Collections.emptySet();
        Set<String> validCities =
                request.getCities() != null ? request.getCities() : Collections.emptySet();
        Set<Long> validUsers =
                request.getUsersId() != null ? request.getUsersId() : Collections.emptySet();
        Boolean isApproved = request.getApprovalStatus();

        log.info("Mapping filtered images...");
        Set<ImageFilterResponseDTO> imageFilterResponseDTOS = imageMapper.mapGetImageByFilter(
                filterImages(validCategories, validCities, validUsers, isApproved));
        log.info("Mapping filtered images completed successfully...");

        log.info("[OPERATION] Filtering images by criteria completed successfully.");
        return imageFilterResponseDTOS;
    }

    private Set<Image> filterImages(Set<String> validCategories, Set<String> validCities,
                                    Set<Long> validUsers, Boolean isApproved) {
        log.info("Finding images by criteria. Categories: {}, cities: {}, users ids: {}, approved: {} "
                , validCategories
                , validCities
                , validUsers
                , isApproved);


        Set<Image> images;
        images = imageRepository.findAll().stream()
                .filter(image ->
                        (validCategories.isEmpty() ||
                                validCategories.contains(image.getCategory().getName())) &&
                                (validCities.isEmpty() ||
                                        validCities.contains(image.getCityName())) &&
                                (validUsers.isEmpty() ||
                                        validUsers.contains(image.getUser().getId())) &&
                                (isApproved == null || image.isApproved() == isApproved))
                .collect(Collectors.toSet());
        log.info("Filtering completed. Images to return: {}", images);
        return images;
    }

    @Override
    @Transactional
    public void deleteImage(Long imageId) {
        log.info("[OPERATION] Deleting image with id: {}", imageId);
        log.info("Finding image entity...");
        Image image = imageRepository
                .findById(imageId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Image not found with id: " + imageId));

        log.info("Deleting image from other users favourites...");
        image.getUsers().forEach(user -> user.getFavourites().remove(image));
        userRepository.saveAll(image.getUsers());

        imageRepository.delete(image);
        log.info("[OPERATION] Image with id: {} has been successfully deleted", imageId);
    }

    @Override
    public CreateImageResponseDTO createImage(MultipartFile file, CreateImageRequestDTO request)
            throws FileUploadException {
        log.info("[OPERATION] Creating new image from request: {}", request);
        log.info("Preparing image to store...");

        Image image = new Image();
        try {
            image.setFile(file.getBytes());
        } catch (IOException e) {
            throw new FileUploadException("Cannot get bytes information from uploaded file.");
        }
        image.setCityName(request.getCity());
        image.setCategory(categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category not found with id: " + request.getCategoryId())));
        image.setDescription(request.getDescription());
        image.setUser(userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with id: " + request.getUserId())));
        image.setStatus(StatusType.TO_REVIEW);
        log.info("Image preparation completed successfully. Image: {}", image);

        // TODO: add database exception with proper status code
        log.info("Saving image in database...");
        Image savedImage = imageRepository.save(image);
        log.info("[OPERATION] New image saved successfully in database: {}", savedImage);

        log.info("Returning image dto...");
        return imageMapper.mapToCreateImageDTO(image);
    }
}