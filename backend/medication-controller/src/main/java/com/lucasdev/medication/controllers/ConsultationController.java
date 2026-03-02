package com.lucasdev.medication.controllers;

import com.lucasdev.medication.dto.ConsultationDTO;
import com.lucasdev.medication.services.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "consultation")
public class ConsultationController {

    @Autowired
    private ConsultationService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ConsultationDTO> findById(@PathVariable Long id) {
        ConsultationDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }


}
