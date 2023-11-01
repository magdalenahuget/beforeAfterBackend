package com.mpm.beforeandafter.user.service;

import com.mpm.beforeandafter.exception.ResourceNotFoundException;
import com.mpm.beforeandafter.role.model.Role;
import com.mpm.beforeandafter.role.repository.RoleDAO;
import com.mpm.beforeandafter.role.type.RolesType;
import com.mpm.beforeandafter.user.dto.CreateAboutMeRequest;
import com.mpm.beforeandafter.user.dto.CreateUserRequest;
import com.mpm.beforeandafter.user.model.StatusType;
import com.mpm.beforeandafter.user.model.User;
import com.mpm.beforeandafter.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final String USER_NOT_FOUND_MSG_TEMPLATE_LOG_ERROR =
            "There isn't user with the given ID: {}";
    private final String USER_NOT_FOUND_MSG_TEMPLATE_EXCEPTION =
            "There isn't user with the given ID: ";

    private final UserRepository userRepository;
    private final RoleDAO roleDAO;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleDAO roleDAO) {
        this.userRepository = userRepository;
        this.roleDAO = roleDAO;
    }

    @Override
    public List<User> getUsers(RolesType roleType) {
        List<User> users;
        if (roleType == null) {
            users = userRepository.findAll();
        } else {
            users = userRepository.findByRoleName(roleType.getRoleName());
        }
        return users;
    }

    @Override
    public User createUser(CreateUserRequest userDto, RolesType roleType) {
        User user = new User();
        user.setName(userDto.getUserName());
        user.setEmail(userDto.getUserEmail());
        user.setPassword(userDto.getUserPassword());
        Role role = roleDAO.findByName(roleType.getRoleName());
        user.setRole(role);
        user.setStatus(StatusType.TO_REVIEW);
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        log.debug("Getting user by id: {}", userId);
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> {
                    log.error("There is no user with the given ID: {}", userId);
                    return new RuntimeException(
                            "There is no user with the given ID: {}" + userId);
                });
        return user;
    }

    @Override
    public User getAboutMeByUserId(Long userId) {
        log.debug("Getting user about me by id: {}", userId);
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> {
                    log.error("There is no user with the given ID: {}", userId);
                    return new RuntimeException(
                            "There is no user with the given ID: {}" + userId);
                });
        return user;
    }

    @Override
    public User updateUserByAboutMe(Long userId, CreateAboutMeRequest aboutMe) {
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
