package com.porfolio.lucascampodonico.profile;

import java.util.Optional;
import java.util.List;
import org.springframework.data.domain.Sort;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IProfileRepository extends JpaRepository<Profile, Integer>{
    
    Optional<Profile> findById(Integer id);
    List<Profile> findAll(Sort sort);

}
