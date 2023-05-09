package com.carService.product.service;

import com.carService.product.entity.User;
import com.carService.product.entity.enums.Role;
import com.carService.product.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    public boolean createUser(User user){
        String email = user.getEmail();
        if(userRepo.findByEmail(email) != null)
            return false;
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        log.info("Saving new User with email: {}",email);
        userRepo.save(user);
        return true;
    }



}
