package com.lucasdev.medication.services;

import com.lucasdev.medication.dto.MedicationDTO;
import com.lucasdev.medication.dto.MedicationRecordDTO;
import com.lucasdev.medication.entities.Medication;
import com.lucasdev.medication.entities.MedicationRecord;
import com.lucasdev.medication.repositories.MedicationRecordRepository;
import com.lucasdev.medication.repositories.MedicationRepository;
import com.lucasdev.medication.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MedicineService {

    @Autowired
    private MedicationRecordRepository recordRepository;

    @Autowired
    private MedicationRepository medicationRepository;

    @Transactional(readOnly = true)
    public MedicationDTO findById(Long id) {
        Medication entity = medicationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Medicamento não encontrado"));
        return new MedicationDTO(entity);
    }

    @Transactional
    public MedicationRecordDTO insert(Long medicationId, MedicationRecordDTO dto) {
        MedicationRecord entity = new MedicationRecord();

        Medication medication = medicationRepository.getReferenceById(medicationId);
        entity.setMedication(medication);

        entity = recordRepository.save(entity);
        return new MedicationRecordDTO(entity);
    }


}
