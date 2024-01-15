package com.mpm.beforeandafter.favourites.service;

import com.mpm.beforeandafter.exception.ResourceNotFoundException;
import com.mpm.beforeandafter.favourites.dto.AddToFavouritesResponseDto;
import com.mpm.beforeandafter.favourites.dto.DeleteFavouriteResponseDto;
import com.mpm.beforeandafter.favourites.dto.GetFavouriteDTO;
import com.mpm.beforeandafter.image.model.Image;
import com.mpm.beforeandafter.image.repository.ImageRepository;
import com.mpm.beforeandafter.user.model.User;
import com.mpm.beforeandafter.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

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
    private final FavouritesMapper favouritesMapper;

    public FavouritesServiceImpl(ImageRepository imageRepository, UserRepository userRepository,
                                 FavouritesMapper favouritesMapper) {
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
        this.favouritesMapper = favouritesMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<GetFavouriteDTO> getFavouritesByUserId(Long userId) {
        log.info("[ACTION] Getting favourites...");
        log.debug("[REQUEST] Getting favourites by user id: {}", userId);

        User user = findUserById(userId);
        return user.getFavourites().stream()
                .map(image -> new GetFavouriteDTO(image.getId(), image.getFile()))
                .toList();
    }

    @Override
    @Transactional
    public AddToFavouritesResponseDto addImageToFavourites(Long imageId, Long userId) {
        log.info("[ACTION] Attempting to add specified image to the favourites list...");
        log.debug("[REQUEST] Provided image id: {}, for provided user id: {}", imageId, userId);

        Image image = findImageById(imageId);
        User user = findUserById(userId);
        validateImageOwnership(image, user);
        return isImageAlreadyFavourite(image, user)
                ? createAlreadyFavouriteResponse(userId, imageId)
                : addImageToFavourites(user, image);
    }

    @Override
    @Transactional
    public DeleteFavouriteResponseDto deleteFavourite(Long imageId, Long userId) {
        User user = findUserById(userId);
        boolean removed = user.getFavourites().removeIf(img -> img.getId().equals(imageId));
        userRepository.save(user);
        String message = removed ? "Image removed from favourites." : "Image is no longer in " +
                "favourites.";

        log.debug("[RESPONSE] User with ID: {} attempted to remove image with ID: {}: {}", userId, imageId,
                message);
        log.info("[ACTION] Specified image has been successfully deleted from the favourites list.");
        return favouritesMapper.mapToDeleteFavouriteDTO(message);
    }

    private Image findImageById(Long imageId) {
        log.info("[ACTION] Getting image by id.");
        log.debug("[REQUEST] Provided image id: {}", imageId);
        return imageRepository.findById(imageId)
                .orElseThrow(() -> {
                    log.warn(IMAGE_NOT_FOUND_LOG_ERROR_MSG, imageId);
                    return new ResourceNotFoundException(IMAGE_NOT_FOUND_EXCEPTION_MSG + imageId);
                });
    }

    private User findUserById(Long userId) {
        log.info("[ACTION] Getting user by id");
        log.debug("[REQUEST] Provided user id: {}", userId);
        return userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.warn(USER_NOT_FOUND_LOG_ERROR_MSG, userId);
                    return new ResourceNotFoundException(USER_NOT_FOUND_EXCEPTION_MSG + userId);
                });
    }

    private void validateImageOwnership(Image image, User user) {
        log.info("[ACTION] Validating image ownership.");
        log.debug("[REQUEST] Provided user: {}, provided image: {}", user, image);
        if (image.getUser().equals(user)) {
            log.warn("User with ID: {} cannot add their own image with ID: {} to favourites",
                    user.getId(), image.getId());
            throw new IllegalArgumentException("Cannot add own image to favourites.");
        }
    }

    private boolean isImageAlreadyFavourite(Image image, User user) {
        return user.getFavourites().contains(image);
    }

    private AddToFavouritesResponseDto createAlreadyFavouriteResponse(Long userId, Long imageId) {
        log.debug("Image with ID: {} is already in the favourites of user with ID: {}", imageId,
                userId);
        return favouritesMapper.mapToAddedToFavouritesDTO(userId, imageId,
                "Image is already in favourites.");
    }

    private AddToFavouritesResponseDto addImageToFavourites(User user, Image image) {
        user.getFavourites().add(image);
        userRepository.save(user);
        log.info("[ACTION] Specified image added to the favourites successfully.");
        log.debug("[RESPONSE] Image with ID: {} added to the favourites of user with ID: {}", image.getId(),
                user.getId());
        return createAddedToFavouritesResponse(user.getId(), image.getId());
    }

    private AddToFavouritesResponseDto createAddedToFavouritesResponse(Long userId, Long imageId) {
        return favouritesMapper.mapToAddedToFavouritesDTO(userId, imageId,
                "Image added to favourites.");
    }
}