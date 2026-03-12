package com.lucasdev.medication.controllers;

import com.lucasdev.medication.dto.MedicationRecordDTO;
import com.lucasdev.medication.entities.MedicationRecord;
import com.lucasdev.medication.services.MedicationRecordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/medications/{medicationId}/records")
public class MedicationRecordController {

    @Autowired
    private MedicationRecordService service;

    @PostMapping
    public ResponseEntity<MedicationRecordDTO> insert(
            @PathVariable Long medicationId,
            @Valid @RequestBody MedicationRecordDTO dto) {

        dto = service.insert(medicationId, dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<MedicationRecordDTO>> findByMedication(@PathVariable Long medicationId) {
        List<MedicationRecordDTO> list = service.findByMedication(medicationId);
        return ResponseEntity.ok(list);
    }

    @PutMapping(value = "/{recordId}")
    public ResponseEntity<MedicationRecordDTO> update(
            @PathVariable Long medicationId,
            @PathVariable Long recordId,
            @Valid @RequestBody MedicationRecordDTO dto) {

        dto = service.update(recordId, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{recordId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long medicationId,
            @PathVariable Long recordId) {

        service.delete(recordId);
        return ResponseEntity.noContent().build();
    }

}