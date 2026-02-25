package com.lucasdev.medication.dto;

import com.lucasdev.medication.entities.Consultation;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.Instant;

public class ConsultatioDTO {

    private Long id;

    @Size(min = 3, max = 80, message = "Nome precisar ter de 3 a 80 caracteres")
    @NotBlank(message = "Campo requerido")
    private String doctor;

    @Size(min = 3, max = 80, message = "Nome precisar ter de 3 a 80 caracteres")
    @NotBlank(message = "Campo requerido")
    private String specialty;

    @FutureOrPresent
    private Instant date;
    private String status;

    public ConsultatioDTO() {
    }

    public ConsultatioDTO(Long id, String doctor, String specialty, Instant date, String status) {
        this.id = id;
        this.doctor = doctor;
        this.specialty = specialty;
        this.date = date;
        this.status = status;
    }

    public ConsultatioDTO(Consultation entity) {
        id = entity.getId();
        doctor = entity.getDoctor();
        specialty = entity.getSpecialty();
        date = entity.getDate();
        status = entity.getStatus();
    }

    public Long getId() {
        return id;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getSpecialty() {
        return specialty;
    }

    public Instant getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }
}
