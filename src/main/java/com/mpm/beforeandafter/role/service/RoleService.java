package com.mpm.beforeandafter.role.service;

import com.mpm.beforeandafter.role.model.Role;
import com.mpm.beforeandafter.role.repository.RoleDAO;
import com.mpm.beforeandafter.role.type.RolesType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleDAO roleDAO;

    @Autowired
    public RoleService(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    public void createRoles() {
        for (RolesType roleType: RolesType.values()) {
            Role role = new Role(roleType.getRoleName());
            roleDAO.save(role);
        }

    }
}
