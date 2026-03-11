package com.lucasdev.medication.repositories;

import com.lucasdev.medication.entities.MedicationRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicationRecordRepository extends JpaRepository<MedicationRecord, Long> {

    List<MedicationRecord> findByMedicationId(Long medicationId);
}
