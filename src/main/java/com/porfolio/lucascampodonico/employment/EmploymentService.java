package com.porfolio.lucascampodonico.employment;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmploymentService {
    
        @Autowired
        public IEmploymentRepository employmentRepository;

        public Optional<Employment> findById(Integer id) {
            return employmentRepository.findById(id);
        }

        public <S extends Employment> Employment save(@Valid Employment request) {
            return employmentRepository.save(request);
        }

        public List<Employment> findAll(Sort sort) {
            return employmentRepository.findAll(sort);
        }

        public Page<Employment> findAll(Pageable pageable) {
            return employmentRepository.findAll(pageable);
        }

        public void deleteById(Integer id) {
            employmentRepository.deleteById(id);
        }

        
}
