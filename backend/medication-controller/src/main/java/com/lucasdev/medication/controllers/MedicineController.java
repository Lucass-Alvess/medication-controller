package com.lucasdev.medication.controllers;

import com.lucasdev.medication.dto.MedicationDTO;
import com.lucasdev.medication.services.MedicineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/medicine")
public class MedicineController {

    @Autowired
    private MedicineService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<MedicationDTO> findById(@PathVariable Long id) {
        MedicationDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<MedicationDTO> insert(@Valid @RequestBody MedicationDTO dto) {
        dto = service.insert(dto);
        URI uri =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<MedicationDTO> update(@PathVariable Long id, @Valid @RequestBody MedicationDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }
}
