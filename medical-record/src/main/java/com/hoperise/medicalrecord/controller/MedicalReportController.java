package com.hoperise.medicalrecord.controller;

import com.hoperise.medicalrecord.model.MedicalReport;
import com.hoperise.medicalrecord.repository.MedicalReportRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medical-report")
public class MedicalReportController {

    @Autowired
    private final MedicalReportRepository medicalReportRepository;

    public MedicalReportController(MedicalReportRepository medicalReportRepository) {
        this.medicalReportRepository = medicalReportRepository;
    }

    @GetMapping(path = "/all")
    public List<MedicalReport> getAllMedicalReports() {
        return medicalReportRepository.findAll();
    }

    @PostMapping("/create")
    public MedicalReport create(@RequestBody @Valid MedicalReport medicalReport) {
        return medicalReportRepository.save(medicalReport);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (medicalReportRepository.existsById(id)) {
            medicalReportRepository.deleteById(id);
            return ResponseEntity.ok("Medical report deleted successfully"); // Return 200 OK if deletion is successful
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medical report with that ID doesn't exist"); // Return 404 Not Found if entity with the specified ID does not exist
        }
    }

}
