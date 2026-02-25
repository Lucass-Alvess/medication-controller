package com.lucasdev.medication.repositories;

import com.lucasdev.medication.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
}
