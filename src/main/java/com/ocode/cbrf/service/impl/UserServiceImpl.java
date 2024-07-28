package com.ocode.cbrf.service.impl;

import com.ocode.cbrf.model.user.Role;
import com.ocode.cbrf.model.user.User;
import com.ocode.cbrf.repository.RoleRepository;
import com.ocode.cbrf.repository.UserRepository;
import com.ocode.cbrf.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void addUser(String login, String password, String role) {
        User user = new User();

        if (userRepository.findUserByLogin(login).isPresent()) {
            System.out.println("user is already exists");
            return;
        }

        Optional.ofNullable(role).ifPresentOrElse(rl -> {
            Role managedRole = roleRepository.findRoleByRolename(rl).orElseThrow(() ->
                    new NullPointerException("role is null"));
            user.setRole(managedRole);
        }, () -> {
            throw new NullPointerException("role is null");
        });
        Optional.ofNullable(login).ifPresentOrElse(user::setLogin, () -> {
            throw new NullPointerException("login is null");
        });
        Optional.ofNullable(password).ifPresentOrElse(pwd -> {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(6);
            user.setPassword(encoder.encode(pwd));
        }, () -> {
            throw new NullPointerException("password is null");
        });
        user.setIsActive(true);
        user.setIsDeleted(false);

        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(String login) {
        userRepository.findUserByLogin(login).ifPresentOrElse(user -> {
            user.setIsDeleted(true);
            user.setIsActive(false);
            userRepository.save(user);
        }, () -> {
            throw new UsernameNotFoundException(login + " does not exist");
        });
    }

    @Override
    @Transactional
    public void updateUserLogin(String loginOld, String loginNew) {
        userRepository.findUserByLogin(loginOld).ifPresentOrElse(user ->
            Optional.ofNullable(loginNew).ifPresentOrElse(lNew -> {
                user.setLogin(lNew);
                userRepository.save(user);
            }, () -> {
                throw new NullPointerException("new login is null");
            }), () -> {
            throw new UsernameNotFoundException(loginOld + " does not exist");
        });
    }

    @Override
    @Transactional
    public  void updateUserPassword(String login, String password) {
        userRepository.findUserByLogin(login).ifPresentOrElse(user ->
                Optional.ofNullable(password).ifPresentOrElse(pwd -> {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(6);
            user.setPassword(encoder.encode(pwd));
            userRepository.save(user);
        }, () -> {
            throw new NullPointerException("password is null");
        }), () -> {
            throw new UsernameNotFoundException(login + " does not exist");
        });
    }

    @Override
    @Transactional
    public void updateUserIsActive(String login, Boolean activity) {
        userRepository.findUserByLogin(login).ifPresentOrElse(user ->
                Optional.ofNullable(activity).ifPresentOrElse(act -> {
            user.setIsActive(act);
            userRepository.save(user);
        }, () -> {
            throw new NullPointerException("activity is null");
        }), () -> {
            throw new UsernameNotFoundException(login + " does not exist");
        });
    }

    @Override
    public Optional<User> getUser(String login) {
        return userRepository.findUserByLogin(login);
    }
}
