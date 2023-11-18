package com.mpm.beforeandafter;

import jakarta.servlet.MultipartConfigElement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.util.unit.DataSize;

@SpringBootApplication
@EnableAsync
public class BeforeAndAfterApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeforeAndAfterApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(
//            RoleServiceImpl roleServiceImpl,
//            UserService userService,
//            CategoryService categoryService) {
//        return runner -> {
//            roleServiceImpl.createRoles();
//            CreateUserRequestDto admin = new CreateUserRequestDto();
//            admin.setUserName("exampleAdminName");
//            admin.setUserEmail("exampleAdminEmail");
//            admin.setUserPassword("exampleAdminPassword");
//            userService.createUser(admin, RoleType.ADMIN);
//            CreateUserRequestDto user = new CreateUserRequestDto();
//            user.setUserName("exampleUserName");
//            user.setUserEmail("exampleUserEmail");
//            user.setUserPassword("exampleUserPassword");
//            userService.createUser(user, RoleType.USER);
//            CategoryNameRequestDto categoryBeauty = new CategoryNameRequestDto("Beauty");
//            categoryService.createCategory(categoryBeauty);
//        };
//    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.ofBytes(100000000L));
        factory.setMaxRequestSize(DataSize.ofBytes(100000000L));
        return factory.createMultipartConfig();
    }
}