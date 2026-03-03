package com.lucasdev.medication.services;

import com.lucasdev.medication.dto.ConsultationDTO;
import com.lucasdev.medication.entities.Consultation;
import com.lucasdev.medication.repositories.ConsultationRepository;
import com.lucasdev.medication.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConsultationService {

    @Autowired
    private ConsultationRepository repository;

    @Transactional(readOnly = true)
    public ConsultationDTO findById(Long id) {
        Consultation consultation = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new ConsultationDTO(consultation);
    }

    @Transactional(readOnly = true)
   public List<ConsultationDTO> findAll() {
        List<Consultation> result = repository.findAll();
        return result.stream().map(x -> new ConsultationDTO(x)).toList();
   }

   @Transactional
   public ConsultationDTO insert(ConsultationDTO dto) {
        Consultation entity = new Consultation();
        copyDtoToEntity(dto, entity);

        entity = repository.save(entity);
        return new ConsultationDTO(entity);
   }

   @Transactional
   public ConsultationDTO update(Long id, ConsultationDTO dto) {
        try{
            Consultation entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ConsultationDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
   }

   public void copyDtoToEntity(ConsultationDTO dto, Consultation entity) {
        entity.setDoctor(dto.getDoctor());
        entity.setSpecialty(dto.getSpecialty());
        entity.setDate(dto.getDate());
        entity.setStatus(dto.getStatus());
   }


}
