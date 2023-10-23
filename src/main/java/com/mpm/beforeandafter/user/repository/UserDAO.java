package com.mpm.beforeandafter.user.repository;

import com.mpm.beforeandafter.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    List<User> findByRoleName(String roleName);
}