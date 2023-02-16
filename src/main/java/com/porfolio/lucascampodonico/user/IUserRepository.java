package com.porfolio.lucascampodonico.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Integer>{
    
    Optional<User> findByEmail(String email);

}
