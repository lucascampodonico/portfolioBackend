package com.porfolio.lucascampodonico.project;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/project")
@RequiredArgsConstructor
public class ProjectController {
    
    private final ProjectService service;

    @PostMapping("/register")
    public Object register(
      @Valid @RequestBody Project request
    ) {
        return service.save(request);
    }
    
    @GetMapping("/all")
    public List<Project> getProjects(){
      Sort sort = Sort.by(Sort.Direction.ASC, "nameProject");
      return service.findAll(sort);
    }

    @GetMapping("/{id}")
    public Project getById(@PathVariable Integer id){
      return service.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateById(@PathVariable Integer id, @Valid @RequestBody Project body){
      return service.updateById(id, body);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id){
      service.deleteById(id);
    }
}
