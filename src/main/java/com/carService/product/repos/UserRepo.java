package com.carService.product.repos;

import com.carService.product.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByName (String name);
}
