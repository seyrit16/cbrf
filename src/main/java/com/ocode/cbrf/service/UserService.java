package com.ocode.cbrf.service;

import com.ocode.cbrf.model.user.User;

import java.util.Optional;

public interface UserService {
    void addUser(String login, String password, String role);
    void deleteUser(String login);
    void updateUserLogin(String loginOld, String loginNew);
    void updateUserPassword(String login, String password);
    void updateUserIsActive(String login, Boolean activity);
    void update(User user);
    Optional<User> getUser(String login);
}
