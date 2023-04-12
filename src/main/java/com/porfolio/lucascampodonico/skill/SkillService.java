package com.porfolio.lucascampodonico.skill;

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
public class SkillService {
    
        @Autowired
        public ISkillRepository skillRepository;

        public Skill findById(Integer id) {
            return skillRepository.findById(id)
            .orElseThrow(()-> new EntityNotFoundException("No se encontro el empleo"));
        }

        public <S extends Skill> Skill save(Skill request) {
            return skillRepository.save(request);
        }

        public ResponseEntity<Skill> updateById(Integer id, Skill skillUpdated) {
            Skill skill = skillRepository.findById(id)
            .orElseThrow(()-> new EntityNotFoundException("No se encontro el empleo"));
            skill.setNameSkill(skillUpdated.getNameSkill());
            Skill skillUpdatedDB = skillRepository.save(skill);
            return ResponseEntity.ok(skillUpdatedDB);
        }

        public Page<Skill> findAll(Pageable pageable) {
            return skillRepository.findAll(pageable);
        }

        public void deleteById(Integer id) {
            Optional<Skill> employeeOptional = skillRepository.findById(id);
            if (employeeOptional.isPresent()) {
                skillRepository.deleteById(id);
            } else {
                throw new EntityNotFoundException("El empleo no existee");
            }
        }

        public List<Skill> findAll(Sort sort) {
            return skillRepository.findAll(sort);
        }

        

        
}
