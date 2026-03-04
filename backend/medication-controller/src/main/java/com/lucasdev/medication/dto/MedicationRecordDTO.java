package com.lucasdev.medication.dto;

import com.lucasdev.medication.entities.MedicationRecord;

import java.time.Instant;

public class MedicationRecordDTO {

    private Long id;
    private Instant prescribedSchedule;
    private Instant timeTaken;
    private String status;

    public MedicationRecordDTO() {
    }

    public MedicationRecordDTO(Long id, Instant prescribedSchedule, Instant timeTaken, String status) {
        this.id = id;
        this.prescribedSchedule = prescribedSchedule;
        this.timeTaken = timeTaken;
        this.status = status;
    }

    public MedicationRecordDTO(MedicationRecord entity) {
        id = entity.getId();
        prescribedSchedule = entity.getPrescribedSchedule();
        timeTaken = entity.getTimeTaken();
        status = entity.getStatus();
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

}
