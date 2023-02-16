package com.porfolio.lucascampodonico.advice;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex){
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String, String> dataException(DataIntegrityViolationException ex){
        Map<String, String> errorMap = new HashMap<>();
            errorMap.put("error", ex.getCause().getCause().getMessage());
        return errorMap;
    }

    @ExceptionHandler(ConstraintViolationException.class)
     @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> dataException(ConstraintViolationException ex){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(HibernateException.class)
    public Map<String, HibernateException> hibernateException(HibernateException ex){
        Map<String, HibernateException> errorMap = new HashMap<>();
            errorMap.put("error", ex);
        return errorMap;
    }
}
