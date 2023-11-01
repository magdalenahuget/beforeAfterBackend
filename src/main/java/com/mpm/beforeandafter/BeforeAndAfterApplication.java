package com.mpm.beforeandafter;

import com.mpm.beforeandafter.category.dto.CategoryNameRequestDTO;
import com.mpm.beforeandafter.category.service.CategoryService;
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
    CommandLineRunner commandLineRunner(
            RoleService roleService,
            UserService userService,
            CategoryService categoryService) {
        return runner -> {
            roleService.createRoles();
            CreateUserRequest admin = new CreateUserRequest();
            admin.setUserName("exampleAdminName");
            admin.setUserEmail("exampleAdminEmail");
            admin.setUserPassword("exampleAdminPassword");
            userService.createUser(admin, RolesType.ADMIN);
            CreateUserRequest user = new CreateUserRequest();
            user.setUserName("exampleUserName");
            user.setUserEmail("exampleUserEmail");
            user.setUserPassword("exampleUserPassword");
            userService.createUser(user, RolesType.USER);
            CategoryNameRequestDTO categoryBeauty = new CategoryNameRequestDTO("Beauty");
            categoryService.createCategory(categoryBeauty);
        };
    }
}