package com.team08.csci205_final_project.service;

import com.team08.csci205_final_project.model.DTO.User.UserRegister;
import com.team08.csci205_final_project.model.User.CustomUserDetails;
import com.team08.csci205_final_project.model.User.User;
import com.team08.csci205_final_project.repository.UserRepository;
import com.team08.csci205_final_project.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        User currentUser = new User();
        currentUser.setId("user1");
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getPrincipal()).thenReturn(new CustomUserDetails(currentUser)); // Assuming CustomUserDetails has a constructor or setters

    }

    @Test
    public void testUserRegister() {
        UserRegister userRegister = new UserRegister();
        // Set the properties for userRegister
        userRegister.setEmail("test@example.com");
        userRegister.setPassword("password");

        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        User savedUser = new User();
        savedUser.setPassword("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        User result = userService.userRegister(userRegister);

        assertNotNull(result);
        assertEquals("encodedPassword", result.getPassword());
        verify(userRepository).save(any(User.class));
    }

    @Test
    public void testFindUserById() {
        String userId = "123";
        Optional<User> mockUser = Optional.of(new User());
        when(userRepository.findById(userId)).thenReturn(mockUser);

        Optional<User> result = userService.findUserById(userId);

        assertTrue(result.isPresent());
        assertEquals(mockUser.get(), result.get());
        verify(userRepository).findById(userId);
    }

    @Test
    public void testUpdateUser() {
        UserRegister userRegister = new UserRegister();
        // Set the properties for userRegister
        userRegister.setEmail("update@example.com");

        String currentUserId = "user1";
        when(userRepository.findById(currentUserId)).thenReturn(Optional.of(new User()));

        User updatedUser = new User();
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        User result = userService.updateUser(userRegister);

        assertNotNull(result);
        verify(userRepository).save(any(User.class));
    }


}
