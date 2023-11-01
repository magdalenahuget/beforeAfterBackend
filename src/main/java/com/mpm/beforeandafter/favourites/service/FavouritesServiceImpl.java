package com.mpm.beforeandafter.favourites.service;

import com.mpm.beforeandafter.exception.ResourceNotFoundException;
import com.mpm.beforeandafter.favourites.dto.AddToFavouritesResponseDTO;
import com.mpm.beforeandafter.favourites.dto.DeleteFavouriteResponseDTO;
import com.mpm.beforeandafter.image.model.Image;
import com.mpm.beforeandafter.image.repository.ImageRepository;
import com.mpm.beforeandafter.user.model.User;
import com.mpm.beforeandafter.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FavouritesServiceImpl implements FavouritesService {
    private static final String IMAGE_NOT_FOUND_LOG_ERROR_MSG =
            "There is no image found with the given ID: {}";
    private static final String IMAGE_NOT_FOUND_EXCEPTION_MSG =
            "There is no image found with the given ID: ";

    private static final String USER_NOT_FOUND_LOG_ERROR_MSG =
            "There is no user with the given ID: {}";
    private static final String USER_NOT_FOUND_EXCEPTION_MSG =
            "There is no user with the given ID: ";
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;

    public FavouritesServiceImpl(ImageRepository imageRepository, UserRepository userRepository) {
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AddToFavouritesResponseDTO addImageToFavourites(Long imageId, Long userId) {
        log.debug("Adding image with ID: {} to the favourites of user with ID: {}", imageId,
                userId);

        Image image = findImageById(imageId);
        User user = findUserById(userId);

        return addImageToFavouritesForUser(image, user);
    }

    @Override
    public DeleteFavouriteResponseDTO deleteFavourite(Long imageId, Long userId) {
        Image image = findImageById(imageId);
        User user = findUserById(userId);

        image.getUsers().remove(user);
        imageRepository.save(image);

        log.info("Image with ID: {} removed from the favourites of user with ID: {}", imageId, userId);
        return new DeleteFavouriteResponseDTO("Image removed from favourites.");
    }

    private Image findImageById(Long imageId) {
        return imageRepository.findById(imageId)
                .orElseThrow(() -> {
                    log.error(IMAGE_NOT_FOUND_LOG_ERROR_MSG, imageId);
                    return new ResourceNotFoundException(IMAGE_NOT_FOUND_EXCEPTION_MSG + imageId);
                });
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.error(USER_NOT_FOUND_LOG_ERROR_MSG, userId);
                    return new ResourceNotFoundException(USER_NOT_FOUND_EXCEPTION_MSG + userId);
                });
    }

    private AddToFavouritesResponseDTO addImageToFavouritesForUser(Image image, User user) {
        Long imageId = image.getId();
        Long userId = user.getId();

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
}
