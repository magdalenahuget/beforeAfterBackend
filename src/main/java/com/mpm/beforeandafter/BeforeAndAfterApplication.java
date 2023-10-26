package com.mpm.beforeandafter;

import com.mpm.beforeandafter.role.service.RoleService;
import com.mpm.beforeandafter.role.type.RolesType;
import com.mpm.beforeandafter.user.dto.CreateUserRequest;
import com.mpm.beforeandafter.user.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BeforeAndAfterApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeforeAndAfterApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(RoleService roleService, UserService userService) {
        return runner -> {
            roleService.createRoles();
            CreateUserRequest admin = new CreateUserRequest();
            admin.setUserName("exampleAdminName");
            admin.setUserEmail("exampleAdminEmail");
            admin.setUserPassword("exampleAdminPassword");
            userService.createUser(admin, RolesType.ADMIN);
        };
    }
}