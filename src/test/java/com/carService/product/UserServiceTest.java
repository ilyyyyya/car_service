package com.carService.product;

import com.carService.product.entity.User;
import com.carService.product.entity.enums.Role;
import com.carService.product.repos.UserRepo;
import com.carService.product.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepo userRepo;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void createUser_withValdUser(){
        User user = new User();
        user.setEmail("test2@example.com");
        user.setPassword("password123");

        Mockito.when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        Mockito.when(userRepo.findByEmail(user.getEmail())).thenReturn(null);

        boolean success = userService.createUser(user);

        Mockito.verify(userRepo).save(user);

        assertThat(user.getEmail()).isEqualTo("test2@example.com");
        assertThat(user.getPassword()).isEqualTo("encodedPassword");
        assertThat(user.getRoles().contains(Role.ROLE_USER)).isTrue();

        assertThat(success).isTrue();

    }
}
