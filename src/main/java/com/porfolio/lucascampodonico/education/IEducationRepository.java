package com.porfolio.lucascampodonico.education;

import java.util.Optional;
import java.util.List;
import org.springframework.data.domain.Sort;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IEducationRepository extends JpaRepository<Education, Integer>{
    
    Optional<Education> findById(Integer id);
    List<Education> findAll(Sort sort);

}
