package com.ocode.cbrf.config.init;

import com.ocode.cbrf.service.RoleService;
import com.ocode.cbrf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsersInitializer {
    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public UsersInitializer(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {
            roleService.addRole("ADMIN");
            roleService.addRole("USER");

            userService.addUser("admin1", "admin1", "ADMIN");
            userService.addUser("user1", "user1", "USER");
            userService.addUser("user2", "user2", "USER");
        };
    }
}