package com.team08.csci205_final_project.controller;

import com.team08.csci205_final_project.model.Auth.Role;
import com.team08.csci205_final_project.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.team08.csci205_final_project.model.Auth.LoginRequest;
import com.team08.csci205_final_project.model.Auth.LoginResponse;
import com.team08.csci205_final_project.security.JwtUtil;

/**
 * Controller class for authentication-related operations in the Spring application.
 * This class handles the endpoint for user authentication, providing the ability
 * for users to log in and receive a JWT token for subsequent authenticated requests.
 * It utilizes the {@link AuthenticationManager} for user authentication and
 * {@link JwtUtil} for generating JWT tokens.
 *
 **/
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    /**
     * Authenticates a user and generates a JWT token upon successful authentication.
     * This method takes a login request, authenticates the user, and if successful,
     * generates a JWT token which is returned along with user details.
     *
     * @param loginRequest A request object containing the username and password.
     * @return A ResponseEntity containing the JWT token and user details.
     */
    @Operation(summary = "Login to an account",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                    examples = @ExampleObject(
                            value = "{\"username\":\"hqp001@bucknell.edu\",\"password\":1234}"
                    ))
            )
    )
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> createAuthenticationToken(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        final String jwt = jwtUtil.generateToken(authenticate);
        final String fullName = userService.getFullNameByUsername(loginRequest.getUsername());
        final Role role = userService.getRoleByUsername(loginRequest.getUsername());
        final String email = loginRequest.getUsername();

        return ResponseEntity.ok(new LoginResponse(jwt, fullName, email, role));
    }
}
