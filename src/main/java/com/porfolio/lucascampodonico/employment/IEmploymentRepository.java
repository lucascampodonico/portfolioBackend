package com.porfolio.lucascampodonico.employment;

import java.util.Optional;
import java.util.List;
import org.springframework.data.domain.Sort;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmploymentRepository extends JpaRepository<Employment, Integer>{
    
    Optional<Employment> findById(Integer id);
    List<Employment> findAll(Sort sort);

}
