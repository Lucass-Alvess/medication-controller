package com.lucasdev.medication.repositories;

import com.lucasdev.medication.entities.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
}
