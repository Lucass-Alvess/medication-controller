package com.lucasdev.medication.services;

import com.lucasdev.medication.dto.MedicationRecordDTO;
import com.lucasdev.medication.entities.Medication;
import com.lucasdev.medication.entities.MedicationRecord;
import com.lucasdev.medication.repositories.MedicationRecordRepository;
import com.lucasdev.medication.repositories.MedicationRepository;
import com.lucasdev.medication.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MedicationRecordService {

    @Autowired
    private MedicationRecordRepository recordRepository;

    @Autowired
    private MedicationRepository medicationRepository;

    @Transactional
    public MedicationRecordDTO insert(Long medicationId, MedicationRecordDTO dto) {
        try {
            MedicationRecord entity = new MedicationRecord();
            Medication medication = medicationRepository.getReferenceById(medicationId);
            entity.setMedication(medication);
            copyDtoToEntity(dto, entity);
            entity = recordRepository.save(entity);
            return new MedicationRecordDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Medicamento não encontrado com o ID: " + medicationId);
        }
    }

    private void copyDtoToEntity(MedicationRecordDTO dto, MedicationRecord entity) {
        entity.setPrescribedSchedule(dto.getPrescribedSchedule());
        entity.setTimeTaken(dto.getTimeTaken());
        entity.setStatus(dto.getStatus());
    }
}