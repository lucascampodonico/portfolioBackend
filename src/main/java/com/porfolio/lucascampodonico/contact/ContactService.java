package com.porfolio.lucascampodonico.contact;

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
public class ContactService {
    
        @Autowired
        public IContactRepository contactRepository;

        public Contact findById(Integer id) {
            return contactRepository.findById(id)
            .orElseThrow(()-> new EntityNotFoundException("No se encontro el empleo"));
        }

        public <S extends Contact> Contact save(Contact request) {
            return contactRepository.save(request);
        }

        public ResponseEntity<Contact> updateById(Integer id, Contact contactUpdated) {
            Contact contact = contactRepository.findById(id)
            .orElseThrow(()-> new EntityNotFoundException("No se encontro el empleo"));
            contact.setNameContact(contactUpdated.getNameContact());
            contact.setDescription(contactUpdated.getDescription());
            contact.setDateFrom(contactUpdated.getDateFrom());
            contact.setDateTo(contactUpdated.getDateTo());
            Contact contactUpdatedDB = contactRepository.save(contact);
            return ResponseEntity.ok(contactUpdatedDB);
        }

        public Page<Contact> findAll(Pageable pageable) {
            return contactRepository.findAll(pageable);
        }

        public void deleteById(Integer id) {
            Optional<Contact> employeeOptional = contactRepository.findById(id);
            if (employeeOptional.isPresent()) {
                contactRepository.deleteById(id);
            } else {
                throw new EntityNotFoundException("El empleo no existee");
            }
        }

        public List<Contact> findAll(Sort sort) {
            return contactRepository.findAll(sort);
        }

        

        
}
