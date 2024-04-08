package com.hoperise.medicalrecord.controller;

import com.hoperise.medicalrecord.model.MedicalReport;
import com.hoperise.medicalrecord.repository.MedicalReportRepository;
import com.hoperise.medicalrecord.service.DoctorReferralService;
import com.hoperise.medicalrecord.service.MedicalReportService;
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
    private final MedicalReportService medicalReportService;

    public MedicalReportController(MedicalReportService medicalReportService) {
        this.medicalReportService = medicalReportService;
    }

    @GetMapping(path = "/all")
    public @ResponseBody ResponseEntity<List<MedicalReport>> getAllMedicalReports() {
        return new ResponseEntity<>(medicalReportService.getAllMedicalReports(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public @ResponseBody ResponseEntity<MedicalReport> create(@RequestBody @Valid MedicalReport medicalReport) {
        return new ResponseEntity<>(medicalReportService.createMedicalReport(medicalReport), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public @ResponseBody ResponseEntity<String> delete(@PathVariable Long id) {
        return new ResponseEntity<>(medicalReportService.deleteMedicalReport(id), HttpStatus.OK);

    }

}
