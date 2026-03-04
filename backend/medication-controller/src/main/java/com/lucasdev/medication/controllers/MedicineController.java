package com.lucasdev.medication.controllers;

import com.lucasdev.medication.dto.MedicationDTO;
import com.lucasdev.medication.entities.Medication;
import com.lucasdev.medication.services.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
