package com.porfolio.lucascampodonico.user;



import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    
    @GetMapping("/verify")
    public ResponseEntity<Object> isTokenValid() {
        Map<String, Object> body = new HashMap<>();
        body.put("ok", "true");
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
