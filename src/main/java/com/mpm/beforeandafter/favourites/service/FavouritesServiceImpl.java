package com.mpm.beforeandafter.favourites.service;

import com.mpm.beforeandafter.favourites.dto.DeleteFavouriteResponseDTO;
import com.mpm.beforeandafter.image.repository.ImageRepository;
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
        return null;
    }
}
