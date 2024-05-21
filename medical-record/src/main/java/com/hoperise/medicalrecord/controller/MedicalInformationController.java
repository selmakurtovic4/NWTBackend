package com.hoperise.medicalrecord.controller;

import com.hoperise.medicalrecord.model.MedicalInformation;
import com.hoperise.medicalrecord.service.MedicalInformationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medical-information")
public class MedicalInformationController {

    @Autowired
    private final MedicalInformationService medicalInformationService;

    public MedicalInformationController(MedicalInformationService medicalInformationService) {
        this.medicalInformationService = medicalInformationService;
    }

    @GetMapping(path = "/all")
    public @ResponseBody ResponseEntity<List<MedicalInformation>> getAllMedicalInformation() {
        return new ResponseEntity<>(medicalInformationService.getAllMedicalInformation(), HttpStatus.OK);
    }

    @GetMapping(path = "/all/{id}")
    public @ResponseBody ResponseEntity<Optional<MedicalInformation>> getMedicalReport(@PathVariable Long id) {
        return new ResponseEntity<>(medicalInformationService.getMedicalInformation(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public @ResponseBody ResponseEntity<?> createMedicalInformation(@RequestBody @Valid MedicalInformation medicalInformation) {
        var createdInformation = medicalInformationService.createMedicalInformation(medicalInformation);
        if (createdInformation.getId() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", "Medical information for this patient already exists!"));
        }
        return new ResponseEntity<>(createdInformation, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public @ResponseBody ResponseEntity<?> deleteMedicalInformation(@PathVariable Long id) {
        boolean deleted = medicalInformationService.deleteMedicalInformation(id);
        if (deleted) {
            return ResponseEntity.ok(Collections.singletonMap("message", "Medical information deleted successfully!"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", "Medical information with that ID doesn't exist!"));
    }
}
