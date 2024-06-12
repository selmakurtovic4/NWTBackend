package com.hoperise.medicalrecord.controller;

import com.hoperise.medicalrecord.model.MedicalReport;
import com.hoperise.medicalrecord.service.MedicalReportService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medical-report")
public class MedicalReportController {
    @Autowired
    private final MedicalReportService medicalReportService;

    public MedicalReportController(MedicalReportService medicalReportService) {
        this.medicalReportService = medicalReportService;
    }

    @GetMapping(path = "/all")
    public @ResponseBody ResponseEntity<List<MedicalReport>> getAllMedicalReports() {
        return new ResponseEntity<>(medicalReportService.getAllMedicalReports(), HttpStatus.OK);
    }

    @GetMapping(path = "/all/{id}")
    public @ResponseBody ResponseEntity<Optional<MedicalReport>> getMedicalReport(@PathVariable Long id) {
        return new ResponseEntity<>(medicalReportService.getMedicalReport(id), HttpStatus.OK);
    }

    @GetMapping(path = "/all/patient/{id}")
    public @ResponseBody ResponseEntity<Optional<MedicalReport>> getMedicalReportsForPatient(@PathVariable Long id) {
        return new ResponseEntity<>(medicalReportService.getMedicalReportsForPatient(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @PostMapping("/create")
    public @ResponseBody ResponseEntity<?> create(@RequestBody @Valid MedicalReport medicalReport) {
        var createdReport = medicalReportService.createMedicalReport(medicalReport);
        if (createdReport.getId() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", "A medical report for this patient on the given date already exists."));
        }
        return new ResponseEntity<>(createdReport, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @DeleteMapping("/delete/{id}")
    public @ResponseBody ResponseEntity<?> delete(@PathVariable Long id) {
        boolean deleted = medicalReportService.deleteMedicalReport(id);
        if (deleted) {
            return ResponseEntity.ok(Collections.singletonMap("message", "Medical report deleted successfully!"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", "Medical report with that ID doesn't exist!"));
    }
}
