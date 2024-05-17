package com.hoperise.patient.controller;


import com.hoperise.patient.model.Patient;
import com.hoperise.patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patients")
    public List<Patient> getPatientsByLastName(@RequestParam String lastname) {
        return patientService.getPatientsByLastName(lastname);
    }

    @GetMapping("/patients/{id}")
    public Patient getPatientById(@PathVariable Long userId) {
        return patientService.getPatientById(userId);
    }

}

