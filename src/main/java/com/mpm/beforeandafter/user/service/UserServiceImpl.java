package com.mpm.beforeandafter.user.service;

import com.mpm.beforeandafter.role.model.Role;
import com.mpm.beforeandafter.role.repository.RoleDAO;
import com.mpm.beforeandafter.role.type.RolesType;
import com.mpm.beforeandafter.user.dto.UserRequestDto;
import com.mpm.beforeandafter.user.dto.UserResponseDto;
import com.mpm.beforeandafter.user.model.User;
import com.mpm.beforeandafter.user.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final UserDAO userDAO;
    private final RoleDAO roleDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, RoleDAO roleDAO) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
    }

    @Override
    public List<UserResponseDto> getUsers(RolesType roleType) {
        List<User> users;
        if (roleType == null) {
            users = userDAO.findAll();
        } else {
            users = userDAO.findByRoleName(roleType.getRoleName());
        }

        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        for (User user : users) {
            UserResponseDto userResponseDto = new UserResponseDto();
            userResponseDto.setUserName(user.getUserName());
            userResponseDto.setEmail(user.getUserEmail());
            userResponseDto.setPassword(user.getUserPassword());

            Role userRole = user.getRole();
            userResponseDto.setRoleName(userRole.getName());

            userResponseDtos.add(userResponseDto);
        }
        return userResponseDtos;
    }

    @Override
    public User createUser(UserRequestDto userDto) {
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setUserEmail(userDto.getUserEmail());
        user.setUserPassword(userDto.getUserPassword());
        Role role = roleDAO.getReferenceById(1L);
        user.setRole(role);
        return userDAO.save(user);
    }
}