package com.lucasdev.medication.dto;

import com.lucasdev.medication.entities.MedicationRecord;
import com.lucasdev.medication.entities.Medication;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class MedicationDTO {

    private Long id;
    @Size(min = 3, max = 80, message = "Nome precisar ter de 3 a 80 caracteres")
    @NotBlank(message = "Campo requerido")
    private String name;
    @NotBlank(message = "Campo requerido")
    private String dosage;
    @NotBlank(message = "Campo requerido")
    private Integer frequency;
    private Integer stock;
    @NotBlank(message = "Campo requerido")
    private Instant date;
    @NotBlank(message = "Campo requerido")
    private String applicationType;

    private List<MedicationRecordDTO> records = new ArrayList<>();

    public MedicationDTO() {
    }

    public MedicationDTO(Long id, String name, String dosage, Integer frequency, Integer stock, Instant date, String applicationType) {
        this.id = id;
        this.name = name;
        this.dosage = dosage;
        this.frequency = frequency;
        this.stock = stock;
        this.date = date;
        this.applicationType = applicationType;
    }

    public MedicationDTO(Medication entity) {
        id = entity.getId();
        name = entity.getName();
        dosage = entity.getDosage();
        frequency = getFrequency();
        stock = entity.getStock();
        date = entity.getDate();
        applicationType = entity.getApplicationType();
        for (MedicationRecord rec: entity.getRecords()) {
            records.add(new MedicationRecordDTO(rec));
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    public List<MedicationRecordDTO> getRecords() {
        return records;
    }
}
