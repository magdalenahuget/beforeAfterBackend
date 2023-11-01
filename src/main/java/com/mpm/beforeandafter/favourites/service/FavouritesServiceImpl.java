package com.mpm.beforeandafter.favourites.service;

import com.mpm.beforeandafter.favourites.dto.DeleteFavouriteResponseDTO;
import com.mpm.beforeandafter.image.model.Image;
import com.mpm.beforeandafter.image.repository.ImageRepository;
import com.mpm.beforeandafter.user.model.User;
import com.mpm.beforeandafter.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FavouritesServiceImpl implements FavouritesService{

    private final ImageRepository imageRepository;
    private final UserRepository userRepository;

    public FavouritesServiceImpl(ImageRepository imageRepository, UserRepository userRepository) {
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
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
}
