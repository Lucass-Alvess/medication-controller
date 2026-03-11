package com.lucasdev.medication.dto;

import com.lucasdev.medication.entities.Medication;
import java.time.Instant;

public class MedicationMinDTO {

    private Long id;
    private String name;
    private String dosage;
    private Integer frequency;
    private Integer stock;
    private Instant date;
    private String applicationType;

    public MedicationMinDTO() {
    }

    public MedicationMinDTO(Medication entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.dosage = entity.getDosage();
        this.frequency = entity.getFrequency();
        this.stock = entity.getStock();
        this.date = entity.getDate();
        this.applicationType = entity.getApplicationType();
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDosage() { return dosage; }
    public Integer getFrequency() { return frequency; }
    public Integer getStock() { return stock; }
    public Instant getDate() { return date; }
    public String getApplicationType() { return applicationType; }
}