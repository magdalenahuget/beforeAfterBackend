package com.mpm.beforeandafter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BeforeAndAfterApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeforeAndAfterApplication.class, args);
    }
//    @Bean
//    CommandLineRunner commandLineRunner(
//            RoleService roleService,
//            UserService userService,
//            CategoryService categoryService) {
//        return runner -> {
//            roleService.createRoles();
//            CreateUserRequest admin = new CreateUserRequest();
//            admin.setUserName("exampleAdminName");
//            admin.setUserEmail("exampleAdminEmail");
//            admin.setUserPassword("exampleAdminPassword");
//            userService.createUser(admin, RolesType.ADMIN);
//            CreateUserRequest user = new CreateUserRequest();
//            user.setUserName("exampleUserName");
//            user.setUserEmail("exampleUserEmail");
//            user.setUserPassword("exampleUserPassword");
//            userService.createUser(user, RolesType.USER);
//            CategoryNameRequest categoryBeauty = new CategoryNameRequest("Beauty");
//            categoryService.createCategory(categoryBeauty);
//        };
//    }
}