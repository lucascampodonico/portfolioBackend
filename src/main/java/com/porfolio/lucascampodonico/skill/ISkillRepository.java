package com.porfolio.lucascampodonico.skill;

import java.util.Optional;
import java.util.List;
import org.springframework.data.domain.Sort;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ISkillRepository extends JpaRepository<Skill, Integer>{
    
    Optional<Skill> findById(Integer id);
    List<Skill> findAll(Sort sort);

}
