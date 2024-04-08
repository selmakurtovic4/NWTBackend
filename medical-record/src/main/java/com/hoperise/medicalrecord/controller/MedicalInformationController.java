package com.hoperise.medicalrecord.controller;

import com.hoperise.medicalrecord.model.MedicalInformation;
import com.hoperise.medicalrecord.repository.MedicalInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medical-information")
public class MedicalInformationController {

    @Autowired
    private final MedicalInformationRepository medicalInformationRepository;

    public MedicalInformationController(MedicalInformationRepository medicalInformationRepository) {
        this.medicalInformationRepository = medicalInformationRepository;
    }

    @GetMapping(path = "/all")
    public List<MedicalInformation> getAllMedicalInformation() {
        return medicalInformationRepository.findAll();
    }

    @PostMapping("/create")
    public MedicalInformation create(@RequestBody MedicalInformation medicalInformation) {
        return medicalInformationRepository.save(medicalInformation);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (medicalInformationRepository.existsById(id)) {
            medicalInformationRepository.deleteById(id);
            return ResponseEntity.ok("Medical information deleted successfully"); // Return 200 OK if deletion is successful
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medical information with that ID doesn't exist"); // Return 404 Not Found if entity with the specified ID does not exist
        }
    }
}
