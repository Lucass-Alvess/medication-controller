package com.lucasdev.medication.services;

import com.lucasdev.medication.dto.ConsultationDTO;
import com.lucasdev.medication.entities.Consultation;
import com.lucasdev.medication.repositories.ConsultationRepository;
import com.lucasdev.medication.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

   public List<ConsultationDTO> findAll() {
        List<Consultation> result = repository.findAll();
        return result.stream().map(x -> new ConsultationDTO(x)).toList();
   }


}
