package com.mpm.beforeandafter.image.service;

import com.mpm.beforeandafter.category.model.Category;
import com.mpm.beforeandafter.category.repository.CategoryRepository;
import com.mpm.beforeandafter.image.dto.*;
import com.mpm.beforeandafter.image.model.Image;
import com.mpm.beforeandafter.image.repository.ImageRepository;
import com.mpm.beforeandafter.user.model.StatusType;
import com.mpm.beforeandafter.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
    public CreateImageResponseDTO createImage(CreateImageRequestDTO request) {
        log.debug("Creating new Image: {}", request);
        Image image = new Image();
        image.setFile(request.getFile());
        image.setCityName(request.getCity());
        image.setCategory(categoryRepository.getReferenceById(request.getCategoryId()));
        image.setDescription(request.getDescription());
        image.setUser(userRepository.getReferenceById(request.getUserId()));
        image.setStatus(StatusType.TO_REVIEW);
        imageRepository.save(image);
        log.info("New Image created: {}", image);

        return CreateImageResponseDTO.map(image);
    }

    @Override
    public List<Image> getImagesByCategoryAndCity(Long categoryId, String cityName) {
        Category category = categoryRepository.getReferenceById(categoryId);
        return imageRepository.findImagesByCategoryAndCityName(category, cityName);
    }


    @Override
    public Set<ImageFilterResponseDTO> getImagesByDynamicFilter(ImageFilterRequestDTO request) {
        Set<Image> images = new HashSet<>();
        Set<String> validCategories = request.getCategories() != null ? request.getCategories() : Collections.emptySet();
        Set<String> validCities = request.getCities() != null ? request.getCities() : Collections.emptySet();
        Boolean isApproved = request.getApprovalStatus();

        images = imageRepository.findAll().stream()
                .filter(image ->
                        (validCategories.isEmpty() || validCategories.contains(image.getCategory().getName())) && (validCities.isEmpty() ||
                                validCities.contains(image.getCityName()))&& (isApproved == null || image.isApproved() == isApproved))
                .collect(Collectors.toSet());
        System.out.println(images);

        return ImageFilterResponseDTO.map(images);
    }
}
