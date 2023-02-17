package com.porfolio.lucascampodonico.employment;

import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register")
    public Object register(
      @Valid @RequestBody Employment request
    ) {
        return service.save(request);
    }
    
    @GetMapping("/all")
    public List<Employment> getEmployments(){
      Sort sort = Sort.by(Sort.Direction.ASC, "nameEmployment");
      return service.findAll(sort);
    }

    @GetMapping("/{id}")
    public Employment getById(@PathVariable Integer id){
      return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id){
      service.deleteById(id);
    }
}
