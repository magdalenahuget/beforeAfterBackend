package com.mpm.beforeandafter.user.service;

import com.mpm.beforeandafter.user.repository.UserDAO;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
