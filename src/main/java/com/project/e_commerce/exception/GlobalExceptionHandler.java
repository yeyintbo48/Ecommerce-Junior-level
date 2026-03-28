package com.project.e_commerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.project.e_commerce.dtos.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //this body will catch runtime exceptions written in the service layer
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex){
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request", ex.getMessage());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    //this body will catch validation error withoud passing @valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex){
        String errorMessage = ex.getBindingResult().getFieldErrors().stream().map(error -> error.getDefaultMessage()).findFirst().orElse("Validation Error");
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),"Validation Error", errorMessage);
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }  
}
