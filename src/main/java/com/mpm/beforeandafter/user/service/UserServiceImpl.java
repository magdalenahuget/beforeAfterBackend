package com.mpm.beforeandafter.user.service;

import com.mpm.beforeandafter.exception.ResourceNotFoundException;
import com.mpm.beforeandafter.role.model.Role;
import com.mpm.beforeandafter.role.repository.RoleRepository;
import com.mpm.beforeandafter.role.type.RoleType;
import com.mpm.beforeandafter.user.dto.*;
import com.mpm.beforeandafter.user.model.StatusType;
import com.mpm.beforeandafter.user.model.User;
import com.mpm.beforeandafter.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final String USER_NOT_FOUND_MSG_TEMPLATE_LOG_ERROR =
            "There isn't user with the given ID: {}";
    private final String USER_NOT_FOUND_MSG_TEMPLATE_EXCEPTION =
            "There isn't user with the given ID: ";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<GetUserResponseDto> getUsers(RoleType roleType) {
        log.debug("Fetching all users");
        List<User> users;
        if (roleType == null) {
            users = userRepository.findAll();
        } else {
            Role providedRole = roleRepository.findByName(roleType);
            users = userRepository.findAllByRolesIn(Set.of(providedRole.getId()));
        }
        log.info("Getting all users (count): {}", users.size());
        return users.stream()
                .map(userMapper::mapToGetUserResponseDto)
                .toList();
    }

    @Override
    public CreateUserResponseDto createUser(CreateUserRequestDto userDto, RoleType roleType) {
        User user = new User();
        user.setName(userDto.getUserName());
        user.setEmail(userDto.getUserEmail());
        user.setPassword(userDto.getUserPassword());
        System.out.println(userDto);
        System.out.println(roleType);
        Role role = roleRepository.findByName(roleType);
        System.out.println(role);
        user.setRoles(Set.of(role));
        user.setStatus(StatusType.TO_REVIEW);
        userRepository.save(user);
        return userMapper.mapToCreateUserResponseDto(user);
    }

    @Override
    public GetUserResponseDto getUserById(Long userId) {
        log.debug("Getting user by id: {}", userId);
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> {
                    log.error("There is no user with the given id: {}", userId);
                    return new RuntimeException(
                            "User not found with id: {}" + userId);
                });
        return userMapper.mapToGetUserResponseDto(user);
    }

    @Override
    public GetAboutMeResponseDto getAboutMeByUserId(Long userId) {
        log.debug("Getting user about me by id: {}", userId);
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> {
                    log.error("There is no user with the given id: {}", userId);
                    return new RuntimeException(
                            "User not found with id: {}" + userId);
                });
        return userMapper.mapToGetAboutMeResponseDto(user);
    }

    @Override
    public CreateAboutMeResponseDto updateUserByAboutMe(Long userId, CreateAboutMeRequestDto aboutMe) {
        log.debug("Getting user by id: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.error("There is no user with the given id: {}", userId);
                    return new RuntimeException(
                            "User not found with id: {}" + userId);
                });
        user.setAboutMe(aboutMe.getAboutMe());
        userRepository.save(user);
        return userMapper.mapToCreateAboutMeResponseDto(user);
    }

    @Override
    public void deleteUser(Long userId) {

        log.debug("User with id:{}", userId);
        userRepository
                .findById(userId)
                .ifPresentOrElse(user -> {
                            userRepository.delete(user);
                            log.info("User with id: {} deleted successfully", userId);
                        },
                        () -> {
                            log.error(USER_NOT_FOUND_MSG_TEMPLATE_LOG_ERROR, userId);
                            throw new ResourceNotFoundException(USER_NOT_FOUND_MSG_TEMPLATE_EXCEPTION + userId);
                        });
    }
}
