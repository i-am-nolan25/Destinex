package com.team08.csci205_final_project.advice;

import com.team08.csci205_final_project.exception.DuplicateAccountException;
import com.team08.csci205_final_project.exception.ExceptionResponse;
import com.team08.csci205_final_project.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.naming.AuthenticationException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for the application.
 * This class provides centralized exception handling across all
 * {@code @RequestMapping} methods through {@code @ExceptionHandler} methods.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles {@link AccessDeniedException} by returning an UNAUTHORIZED response.
     *
     * @param ex The exception that was thrown.
     * @return ResponseEntity containing details of the exception.
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExceptionResponse(
                new Date(), ex.getMessage(), "Unauthorized, login again"
        ));
    }

    /**
     * Handles {@link BadCredentialsException} indicating bad credentials.
     *
     * @param ex The exception that was thrown.
     * @return ResponseEntity with UNAUTHORIZED status and exception details.
     */
    @ExceptionHandler({BadCredentialsException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ExceptionResponse> handleAuthenticationException(Exception ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExceptionResponse(
                new Date(), ex.getMessage(), "Email not exist or wrong password, please try again"
        ));
    }

    /**
     * Generic exception handler that catches all types of exceptions.
     *
     * @param ex The exception that was thrown.
     * @return ResponseEntity with I_AM_A_TEAPOT status and exception details.
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex) {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(new ExceptionResponse(
                new Date(), ex.getMessage(), ex.getClass().getName()
        ));
    }

    /**
     * Handles {@link MethodArgumentNotValidException} for input validation.
     *
     * @param ex The validation exception that was thrown.
     * @return ResponseEntity with BAD_REQUEST status and details about the validation errors.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        StringBuilder errorsBuilder = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errorsBuilder.append(fieldName).append(": ").append(errorMessage).append("; ");
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(
                new Date(), errorsBuilder.toString(), "Missing Argument"
        ));
    }

    /**
     * Handles {@link DuplicateAccountException} indicating an attempt to create a duplicate account.
     *
     * @param ex The exception that was thrown.
     * @return ResponseEntity with BAD_REQUEST status and exception details.
     */
    @ExceptionHandler(DuplicateAccountException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> handleBadRequestException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(
                new Date(), ex.getMessage(), "Account sign up error"
        ));
    }

    /**
     * Handles {@link ResourceNotFoundException} indicating that a requested resource was not found.
     *
     * @param ex The exception that was thrown.
     * @return ResponseEntity with NOT_FOUND status and exception details.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(
            Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(
                new Date(), ex.getMessage(), "Resource not found in database"
        ));
    }

    /**
     * Handles {@link NoHandlerFoundException} for requests with no handler.
     *
     * @param ex The exception that was thrown.
     * @param request The HttpServletRequest in which this exception occurred.
     * @return ResponseEntity with NOT_FOUND status and exception details.
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionResponse> handleNoHandlerFound(NoHandlerFoundException ex, HttpServletRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(), ex.getMessage(), "The requested URL was not found on the server."
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

}