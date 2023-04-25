package com.porfolio.lucascampodonico.education;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EducationService {
    
        @Autowired
        public IEducationRepository educationRepository;

        public Education findById(Integer id) {
            return educationRepository.findById(id)
            .orElseThrow(()-> new EntityNotFoundException("No se encontro la educación"));
        }

        public <S extends Education> Education save(Education request) {
            return educationRepository.save(request);
        }

        public ResponseEntity<Education> updateById(Integer id, Education educationUpdated) {
            Education education = educationRepository.findById(id)
            .orElseThrow(()-> new EntityNotFoundException("No se encontro la educación"));
            education.setNameEducation(educationUpdated.getNameEducation());
            education.setDescription(educationUpdated.getDescription());
            education.setDateFrom(educationUpdated.getDateFrom());
            education.setDateTo(educationUpdated.getDateTo());
            Education educationUpdatedDB = educationRepository.save(education);
            return ResponseEntity.ok(educationUpdatedDB);
        }

        public Page<Education> findAll(Pageable pageable) {
            return educationRepository.findAll(pageable);
        }

        public void deleteById(Integer id) {
            Optional<Education> employeeOptional = educationRepository.findById(id);
            if (employeeOptional.isPresent()) {
                educationRepository.deleteById(id);
            } else {
                throw new EntityNotFoundException("La educacion no existe.");
            }
        }

        public List<Education> findAll(Sort sort) {
            return educationRepository.findAll(sort);
        }

        

        
}
