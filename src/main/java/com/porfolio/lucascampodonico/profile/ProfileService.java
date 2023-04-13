package com.porfolio.lucascampodonico.profile;

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
public class ProfileService {
    
        @Autowired
        public IProfileRepository profileRepository;

        public Profile findById(Integer id) {
            return profileRepository.findById(id)
            .orElseThrow(()-> new EntityNotFoundException("No se encontro el empleo"));
        }

        public <S extends Profile> Profile save(Profile request) {
            return profileRepository.save(request);
        }

        public ResponseEntity<Profile> updateById(Integer id, Profile profileUpdated) {
            Profile profile = profileRepository.findById(id)
            .orElseThrow(()-> new EntityNotFoundException("No se encontro el empleo"));
            profile.setNameProfile(profileUpdated.getNameProfile());
            profile.setDescription(profileUpdated.getDescription());
            profile.setDateFrom(profileUpdated.getDateFrom());
            profile.setDateTo(profileUpdated.getDateTo());
            Profile profileUpdatedDB = profileRepository.save(profile);
            return ResponseEntity.ok(profileUpdatedDB);
        }

        public Page<Profile> findAll(Pageable pageable) {
            return profileRepository.findAll(pageable);
        }

        public void deleteById(Integer id) {
            Optional<Profile> employeeOptional = profileRepository.findById(id);
            if (employeeOptional.isPresent()) {
                profileRepository.deleteById(id);
            } else {
                throw new EntityNotFoundException("El empleo no existee");
            }
        }

        public List<Profile> findAll(Sort sort) {
            return profileRepository.findAll(sort);
        }

        

        
}
