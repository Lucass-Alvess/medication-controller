package com.lucasdev.medication.services;

import com.lucasdev.medication.dto.MedicationRecordDTO;
import com.lucasdev.medication.entities.Medication;
import com.lucasdev.medication.entities.MedicationRecord;
import com.lucasdev.medication.repositories.MedicationRecordRepository;
import com.lucasdev.medication.repositories.MedicationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

public class MedicineService {

    @Autowired
    private MedicationRecordRepository recordRepository;

    @Autowired
    private MedicationRepository medicationRepository;

    @Transactional
    public MedicationRecordDTO insert(Long medicationId, MedicationRecordDTO dto) {
        MedicationRecord entity = new MedicationRecord();

        Medication medication = medicationRepository.getReferenceById(medicationId);
        entity.setMedication(medication);

        entity = recordRepository.save(entity);
        return new MedicationRecordDTO(entity);
    }


}
