package com.porfolio.lucascampodonico.contact;

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
@RequestMapping("/api/v1/contact")
@RequiredArgsConstructor
public class ContactController {
    
    private final ContactService service;

    @PostMapping("/register")
    public Object register(
      @Valid @RequestBody Contact request
    ) {
        return service.save(request);
    }
    
    @GetMapping("/all")
    public List<Contact> getContacts(){
      Sort sort = Sort.by(Sort.Direction.ASC, "nameContact");
      return service.findAll(sort);
    }

    @GetMapping("/{id}")
    public Contact getById(@PathVariable Integer id){
      return service.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateById(@PathVariable Integer id, @Valid @RequestBody Contact body){
      return service.updateById(id, body);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id){
      service.deleteById(id);
    }
}
