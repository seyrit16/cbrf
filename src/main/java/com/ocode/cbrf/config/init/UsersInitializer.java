package com.ocode.cbrf.config.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class UsersInitializer {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {
            jdbcTemplate.execute(
                    "INSERT INTO role (id, role) VALUES " +
                            "(1, 'ADMIN'), " +
                            "(2, 'USER')");

            jdbcTemplate.execute(
                    "INSERT INTO cbrf_user (login, password, role_id, is_active, is_deleted) VALUES " +
                            "('admin1', '$2a$06$kk9kaFR95ZnwbY4W0HOLC.dNpR4JdUgi5OTOMHs4KE3GZtyBh5hiG', 1, true, false), " +
                            "('user1', '$2a$06$08fd96JBy9nVAJOgJTtxsOpk8Zgj6ZABYsZ7UUXTg1lunRjMsKWey', 2, true, false), " +
                            "('user2', '$2a$06$XpcVi9WLhZKGk.U4jKx/7.2O5UyhGEgJAji6G6/glJVzBCO5jBB3y', 2, true, false)");
        };
    }
}