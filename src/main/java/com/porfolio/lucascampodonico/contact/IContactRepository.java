package com.porfolio.lucascampodonico.contact;

import java.util.Optional;
import java.util.List;
import org.springframework.data.domain.Sort;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IContactRepository extends JpaRepository<Contact, Integer>{
    
    Optional<Contact> findById(Integer id);
    List<Contact> findAll(Sort sort);

}
