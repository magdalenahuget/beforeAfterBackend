package com.mpm.beforeandafter.role.repository;

import com.mpm.beforeandafter.role.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDAO extends JpaRepository<Role, Long> {

}
