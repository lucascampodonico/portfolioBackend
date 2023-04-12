package com.porfolio.lucascampodonico.project;

import java.util.Optional;
import java.util.List;
import org.springframework.data.domain.Sort;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IProjectRepository extends JpaRepository<Project, Integer>{
    
    Optional<Project> findById(Integer id);
    List<Project> findAll(Sort sort);

}
