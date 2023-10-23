package com.mpm.beforeandafter;

import com.mpm.beforeandafter.role.service.RoleService;
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
    CommandLineRunner commandLineRunner(RoleService roleService) {
        return runner -> roleService.createRoles();
    }
}