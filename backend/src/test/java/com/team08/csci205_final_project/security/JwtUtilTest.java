package com.team08.csci205_final_project.security;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.Field;
import java.util.ArrayList;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class JwtUtilTest {

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void generateToken() {
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("user");

        String token = jwtUtil.generateToken(authentication);
        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    public void extractUsername() {
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("user");

        String token = jwtUtil.generateToken(authentication);
        assertEquals("user", jwtUtil.extractUsername(token));
    }

    @Test
    public void validateToken_Valid() {
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("user");

        String token = jwtUtil.generateToken(authentication);
        UserDetails userDetails = new User("user", "password", new ArrayList<>());

        assertTrue(jwtUtil.validateToken(token, userDetails));
    }

//    @Test
//    public void testValidateToken_Expired() throws IllegalAccessException, NoSuchFieldException {
//        Authentication authentication = mock(Authentication.class);
//        when(authentication.getName()).thenReturn("user");
//
//        String token = jwtUtil.generateToken(authentication);
//        UserDetails userDetails = new User("user", "password", new ArrayList<>());
//
//        assertFalse(jwtUtil.validateToken(token, userDetails));
//    }

}