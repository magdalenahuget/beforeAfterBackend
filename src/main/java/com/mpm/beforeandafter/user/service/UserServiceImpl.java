package com.mpm.beforeandafter.user.service;

import com.mpm.beforeandafter.role.model.Role;
import com.mpm.beforeandafter.role.repository.RoleDAO;
import com.mpm.beforeandafter.role.type.RolesType;
import com.mpm.beforeandafter.user.dto.UserAboutMeRequestDto;
import com.mpm.beforeandafter.user.dto.UserAboutMeResponseDto;
import com.mpm.beforeandafter.user.dto.UserRequestDto;
import com.mpm.beforeandafter.user.dto.UserResponseDto;
import com.mpm.beforeandafter.user.mapper.UserMapper;
import com.mpm.beforeandafter.user.model.StatusType;
import com.mpm.beforeandafter.user.model.User;
import com.mpm.beforeandafter.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleDAO roleDAO;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleDAO roleDAO, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleDAO = roleDAO;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserResponseDto> getUsers(RolesType roleType) {
        List<User> users;
        if (roleType == null) {
            users = userRepository.findAll();
        } else {
            users = userRepository.findByRoleName(roleType.getRoleName());
        }

        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        for (User user : users) {
            UserResponseDto userResponseDto = new UserResponseDto();
            userResponseDto.setUserName(user.getName());
            userResponseDto.setEmail(user.getEmail());

            Role userRole = user.getRole();
            userResponseDto.setRoleName(userRole.getName());

            userResponseDtos.add(userResponseDto);
        }
        return userResponseDtos;
    }

    @Override
    public User createUser(UserRequestDto userDto) {
        User user = new User();
        user.setName(userDto.getUserName());
        user.setEmail(userDto.getUserEmail());
        user.setPassword(userDto.getUserPassword());
        Role role = roleDAO.getReferenceById(1L);
        user.setRole(role);
        user.setStatus(StatusType.TO_REVIEW);
        return userRepository.save(user);
    }

    @Override
    public UserResponseDto getUserById(Long userId) {
        log.debug("Getting user by id: {}", userId);
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> {
                    log.error("There is no user with the given ID: {}", userId);
                    return new RuntimeException(
                            "There is no user with the given ID: {}" + userId);
                });
        System.out.println("User type of object = " + user);
        UserResponseDto userResponseDto = userMapper.mapUserToDto(user);
        System.out.println("UserResponseDto type of object = " + userResponseDto);
        return userResponseDto;
    }

    @Override
    public UserAboutMeResponseDto getAboutMeByUserId(Long userId) {
        log.debug("Getting user about me by id: {}", userId);
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> {
                    log.error("There is no user with the given ID: {}", userId);
                    return new RuntimeException(
                            "There is no user with the given ID: {}" + userId);
                });
        UserAboutMeResponseDto userAboutMeResponseDto = userMapper.mapUserToAboutMeDto(user);
        return userAboutMeResponseDto;
    }

    @Override
    public User updateUserByAboutMe(Long userId, UserAboutMeRequestDto aboutMe) {
        log.debug("Getting user by id: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.error("There is no user with the given ID: {}", userId);
                    return new RuntimeException(
                            "There is no user with the given ID: {}" + userId);
                });
        user.setAboutMe(aboutMe.getAboutMe());
        return userRepository.save(user);
    }

}