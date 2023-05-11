package com.porfolio.lucascampodonico.businessCard;

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
public class BusinessCardService {
    
        @Autowired
        public IBusinessCardRepository businessCardRepository;

        public BusinessCard findById(Integer id) {
            return businessCardRepository.findById(id)
            .orElseThrow(()-> new EntityNotFoundException("No se encontro el empleo"));
        }

        public <S extends BusinessCard> BusinessCard save(BusinessCard request) {
            return businessCardRepository.save(request);
        }

        public ResponseEntity<BusinessCard> updateById(Integer id, BusinessCard businessCardUpdated) {
            BusinessCard businessCard = businessCardRepository.findById(id)
            .orElseThrow(()-> new EntityNotFoundException("No se encontro el empleo"));
            businessCard.setName(businessCardUpdated.getName());
            businessCard.setResidence(businessCardUpdated.getResidence());
            businessCard.setCity(businessCardUpdated.getCity());
            businessCard.setAge(businessCardUpdated.getAge());
            businessCard.setLinkedin(businessCardUpdated.getLinkedin());
            businessCard.setGithub(businessCardUpdated.getGithub());
            businessCard.setGitlab(businessCardUpdated.getGitlab());
            businessCard.setTwitter(businessCardUpdated.getTwitter());
            businessCard.setImageUrl(businessCardUpdated.getImageUrl());
            BusinessCard businessCardUpdatedDB = businessCardRepository.save(businessCard);
            return ResponseEntity.ok(businessCardUpdatedDB);
        }

        public Page<BusinessCard> findAll(Pageable pageable) {
            return businessCardRepository.findAll(pageable);
        }

        public void deleteById(Integer id) {
            Optional<BusinessCard> employeeOptional = businessCardRepository.findById(id);
            if (employeeOptional.isPresent()) {
                businessCardRepository.deleteById(id);
            } else {
                throw new EntityNotFoundException("El empleo no existe");
            }
        }

        public List<BusinessCard> findAll(Sort sort) {
            return businessCardRepository.findAll(sort);
        }

        

        
}
