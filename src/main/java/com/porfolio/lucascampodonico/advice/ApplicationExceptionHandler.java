package com.porfolio.lucascampodonico.advice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.hibernate.HibernateException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.persistence.EntityNotFoundException;
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

    //Excepcion de id no encontrado en metodo DELETE
    @ExceptionHandler(value = {EmptyResultDataAccessException.class})
    @ResponseBody
    public ResponseEntity<String> handleEmptyResultDataAccessException(EmptyResultDataAccessException e) {
        return new ResponseEntity<>("El empleado no existe", HttpStatus.NOT_FOUND);
    }

    //Excepcion de dato no encontrado en db.
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleEntityNotFoundException(EntityNotFoundException ex) {
        String message = ex.getMessage();
        int statusCode = HttpStatus.NOT_FOUND.value();
        return new ErrorResponse(statusCode, message);
    }

    //Excepcion en metodo de autenticación.
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("mensaje", "Credenciales inválidas");

        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    //Excepcion generica.
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex) {
        return ResponseEntity.status(ex.getStatusCode())
                .body(new ErrorResponse(ex.getStatusCode().value(), ex.getReason()));
    } 


    @ExceptionHandler(MailSendException.class)
    public ResponseEntity<String> handleMailSendException(MailSendException ex) {
        // Obtener el mensaje de error de la excepción
        String errorMessage = "Error al enviar correo"; // Mensaje personalizado
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    public static class ErrorResponse {
        private int status;
        private String message;

        public ErrorResponse(int status, String message) {
            this.status = status;
            this.message = message;
        }

        public int getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }
    }

}


