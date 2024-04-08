package com.hoperise.medicalrecord.controller;

import com.hoperise.medicalrecord.model.MedicalInformation;
import com.hoperise.medicalrecord.service.MedicalInformationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/create")
    public @ResponseBody ResponseEntity<MedicalInformation> createMedicalInformation(@RequestBody @Valid MedicalInformation medicalInformation) {
        return new ResponseEntity<>(medicalInformationService.createMedicalInformation(medicalInformation), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public @ResponseBody ResponseEntity<String> deleteMedicalInformation(@PathVariable Long id) {
        return new ResponseEntity<>(medicalInformationService.deleteMedicalInformation(id), HttpStatus.OK);
    }
}
