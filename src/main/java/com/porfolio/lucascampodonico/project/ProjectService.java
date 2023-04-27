package com.porfolio.lucascampodonico.project;

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
public class ProjectService {
    
        @Autowired
        public IProjectRepository projectRepository;

        public Project findById(Integer id) {
            return projectRepository.findById(id)
            .orElseThrow(()-> new EntityNotFoundException("Project not found."));
        }

        public <S extends Project> Project save(Project request) {
            return projectRepository.save(request);
        }

        public ResponseEntity<Project> updateById(Integer id, Project projectUpdated) {
            Project project = projectRepository.findById(id)
            .orElseThrow(()-> new EntityNotFoundException("Project not found."));
            project.setNameProject(projectUpdated.getNameProject());
            project.setImageUrl(projectUpdated.getImageUrl());
            Project projectUpdatedDB = projectRepository.save(project);
            return ResponseEntity.ok(projectUpdatedDB);
        }

        public Page<Project> findAll(Pageable pageable) {
            return projectRepository.findAll(pageable);
        }

        public void deleteById(Integer id) {
            Optional<Project> employeeOptional = projectRepository.findById(id);
            if (employeeOptional.isPresent()) {
                projectRepository.deleteById(id);
            } else {
                throw new EntityNotFoundException("The project does not exist.");
            }
        }

        public List<Project> findAll(Sort sort) {
            return projectRepository.findAll(sort);
        }

        

        
}
