package com.hoperise.patient.controller;


import com.hoperise.patient.model.Patient;
import com.hoperise.patient.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

   /*public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }*/
/*
    @GetMapping("/patientsbylastname")
    public List<Patient> getPatientsByLastName(@RequestParam String last_name) {
        return patientService.getPatientsByLastName(last_name);
    }*/

    @GetMapping("/patients/{id}")
    public Patient getPatientById(@PathVariable Long user_id) {
        return patientService.getPatientById(user_id);
    }

    @PostMapping("/create")
    public @ResponseBody ResponseEntity<Patient> createPatient(@RequestBody @Valid Patient patient) {
        return new ResponseEntity<>(patientService.createPatient(patient), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public @ResponseBody ResponseEntity<String> deletePatient(@PathVariable Long id) {
        return new ResponseEntity<>(patientService.deletePatient(id), HttpStatus.OK);
    }

}

