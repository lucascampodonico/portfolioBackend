package com.porfolio.lucascampodonico.employment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/employment")
@RequiredArgsConstructor
public class EmploymentController {
    
    private final EmploymentService service;

    @PostMapping("/register")
    public Object register(
      @Valid @RequestBody Employment request
    ) {
        service.save(request);
        return request;
    }
    
}
