package com.lucasdev.medication.repositories;

import com.lucasdev.medication.entities.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
}
