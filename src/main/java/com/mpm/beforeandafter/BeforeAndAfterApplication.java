package com.mpm.beforeandafter;

import com.mpm.beforeandafter.category.dto.CategoryNameRequestDTO;
import com.mpm.beforeandafter.category.service.CategoryService;
import com.mpm.beforeandafter.role.service.RoleServiceImpl;
import com.mpm.beforeandafter.role.type.RolesType;
import com.mpm.beforeandafter.user.dto.CreateUserRequestDto;
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
            RoleServiceImpl roleServiceImpl,
            UserService userService,
            CategoryService categoryService) {
        return runner -> {
            roleServiceImpl.createRoles();
            CreateUserRequestDto admin = new CreateUserRequestDto();
            admin.setUserName("exampleAdminName");
            admin.setUserEmail("exampleAdminEmail");
            admin.setUserPassword("exampleAdminPassword");
            userService.createUser(admin, RolesType.ADMIN);
            CreateUserRequestDto user = new CreateUserRequestDto();
            user.setUserName("exampleUserName");
            user.setUserEmail("exampleUserEmail");
            user.setUserPassword("exampleUserPassword");
            userService.createUser(user, RolesType.USER);
            CategoryNameRequestDTO categoryBeauty = new CategoryNameRequestDTO("Beauty");
            categoryService.createCategory(categoryBeauty);
        };
    }
}