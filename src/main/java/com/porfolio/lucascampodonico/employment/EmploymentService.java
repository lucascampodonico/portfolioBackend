package com.porfolio.lucascampodonico.employment;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmploymentService {
    
        @Autowired
        public IEmploymentRepository employmentRepository;

        public Employment findById(Integer id) {
            return employmentRepository.findById(id)
            .orElseThrow(()-> new EntityNotFoundException("No se encontro el empleo"));
        }

        public <S extends Employment> Employment save(Employment request) {
            return employmentRepository.save(request);
        }

        public Page<Employment> findAll(Pageable pageable) {
            return employmentRepository.findAll(pageable);
        }

        public void deleteById(Integer id) {
            Optional<Employment> employeeOptional = employmentRepository.findById(id);
            if (employeeOptional.isPresent()) {
                employmentRepository.deleteById(id);
            } else {
                throw new EntityNotFoundException("El empleo no existee");
            }
        }

        public List<Employment> findAll(Sort sort) {
            return employmentRepository.findAll(sort);
        }

        

        
}
