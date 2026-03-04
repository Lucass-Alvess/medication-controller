package com.lucasdev.medication.repositories;

import com.lucasdev.medication.entities.MedicationRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRecordRepository extends JpaRepository<MedicationRecord, Long> {
}
