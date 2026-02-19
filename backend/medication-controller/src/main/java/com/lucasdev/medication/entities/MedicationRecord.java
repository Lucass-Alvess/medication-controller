package com.lucasdev.medication.entities;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_medication_record")
public class MedicationRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant prescribedSchedule;
    private Instant timeTaken;
    private String status;

    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    public MedicationRecord() {
    }

    public MedicationRecord(Long id, Instant prescribedSchedule, Instant timeTaken, String status, Medicine medicine) {
        this.id = id;
        this.prescribedSchedule = prescribedSchedule;
        this.timeTaken = timeTaken;
        this.status = status;
        this.medicine = medicine;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getPrescribedSchedule() {
        return prescribedSchedule;
    }

    public void setPrescribedSchedule(Instant prescribedSchedule) {
        this.prescribedSchedule = prescribedSchedule;
    }

    public Instant getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(Instant timeTaken) {
        this.timeTaken = timeTaken;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MedicationRecord that = (MedicationRecord) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
