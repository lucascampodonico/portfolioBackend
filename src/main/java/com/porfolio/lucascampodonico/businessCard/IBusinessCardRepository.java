package com.porfolio.lucascampodonico.businessCard;

import java.util.Optional;
import java.util.List;
import org.springframework.data.domain.Sort;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IBusinessCardRepository extends JpaRepository<BusinessCard, Integer>{
    
    Optional<BusinessCard> findById(Integer id);
    List<BusinessCard> findAll(Sort sort);

}
