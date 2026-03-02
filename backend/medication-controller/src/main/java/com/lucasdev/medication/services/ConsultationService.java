package com.lucasdev.medication.services;

import com.lucasdev.medication.dto.ConsultationDTO;
import com.lucasdev.medication.entities.Consultation;
import com.lucasdev.medication.repositories.ConsultationRepository;
import com.lucasdev.medication.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
