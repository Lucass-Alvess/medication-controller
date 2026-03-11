package com.lucasdev.medication.services;

import com.lucasdev.medication.dto.MedicationDTO;
import com.lucasdev.medication.dto.MedicationMinDTO;
import com.lucasdev.medication.entities.Medication;
import com.lucasdev.medication.repositories.MedicationRecordRepository;
import com.lucasdev.medication.repositories.MedicationRepository;
import com.lucasdev.medication.services.exceptions.DatabaseExeception;
import com.lucasdev.medication.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional(readOnly = true)
    public List<MedicationMinDTO> findAll() {
        List<Medication> result = medicationRepository.findAll();
        return result.stream().map(MedicationMinDTO::new).toList();
    }
    @Transactional
    public MedicationDTO insert(MedicationDTO dto) {
        Medication entity = new Medication();
        copyDtoToEntity(dto, entity);
        entity = medicationRepository.save(entity);
        return new MedicationDTO(entity);
    }

    @Transactional
    public MedicationDTO update(Long id, MedicationDTO  dto) {
        try{
            Medication entity =  medicationRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = medicationRepository.save(entity);
            return new MedicationDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!medicationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            medicationRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseExeception("Falha de integridade referencial");
        }
    }

    private void copyDtoToEntity(MedicationDTO dto, Medication entity) {
        entity.setName(dto.getName());
        entity.setDosage(dto.getDosage());
        entity.setFrequency(dto.getFrequency());
        entity.setStock(dto.getStock());
        entity.setDate(dto.getDate());
        entity.setApplicationType(dto.getApplicationType());
    }


}
