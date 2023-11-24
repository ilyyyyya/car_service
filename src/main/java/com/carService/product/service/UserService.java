package com.carService.product.service;

import com.carService.product.entity.User;
import com.carService.product.entity.enums.Role;
import com.carService.product.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;


    public boolean createUser(User user) {
        String email = user.getEmail();
        if (userRepo.findByEmail(email) != null)
            return false;
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        log.info("Saving new User with email: {}", email);
        userRepo.save(user);
        return true;
    }

    public User findByUsername(String username) {
        return userRepo.findByEmail(username);
    }

    public List<User> usersList() {
        return userRepo.findAll();
    }


    public void banUser(Long id) {
        User user = userRepo.findById(id).orElse(null);
        if (user != null) {
            if (user.isActive()) {
                user.setActive(false);
                log.info("Ban user with id = {}; email: {}", user.getId(), user.getEmail());
            } else {
                user.setActive(true);
                log.info("Unban user with id = {}; email: {}", user.getId(), user.getEmail());
            }

        }
        userRepo.save(user);

    }


    public void changeUserRoles(User user, List<String> selectedRoles) {
        user.getRoles().clear();
        for (String roleName : selectedRoles) {
            Role role = Role.valueOf(roleName);
            user.getRoles().add(role);
        }
        userRepo.save(user);
    }


}
