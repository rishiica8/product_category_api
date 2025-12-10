package com.rishika.product_category_api.advice;

import com.rishika.product_category_api.dtos.ErrorDTO;
import com.rishika.product_category_api.exception.CategoryNotFoundException;
import com.rishika.product_category_api.exception.DuplicateNameException;
import com.rishika.product_category_api.exception.ProductNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 400 - Bad Request (e.g., invalid input)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDTO> handleIllegalArgumentException(
            IllegalArgumentException ex,
            HttpServletRequest request) {

        ErrorDTO error = new ErrorDTO(
                ex.getMessage(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // 404 - Product Not Found
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleProductNotFound(
            ProductNotFoundException ex,
            HttpServletRequest request) {

        ErrorDTO error = new ErrorDTO(
                ex.getMessage(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // 404 - Category Not Found
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleCategoryNotFoundException(
            CategoryNotFoundException ex,
            HttpServletRequest request) {

        ErrorDTO error = new ErrorDTO(
                ex.getMessage(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // 409 - Duplicate Name
    @ExceptionHandler(DuplicateNameException.class)
    public ResponseEntity<ErrorDTO> handleDuplicateNameException(
            DuplicateNameException ex,
            HttpServletRequest request) {

        ErrorDTO error = new ErrorDTO(
                ex.getMessage(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    // 400 - Validation Errors (DTO @Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleValidationErrors(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .findFirst()
                .orElse("Validation error");

        ErrorDTO error = new ErrorDTO(message, request.getRequestURI());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}


