package com.hoperise.medicalrecord.controller;

import com.hoperise.medicalrecord.model.MedicalReport;
import com.hoperise.medicalrecord.repository.MedicalReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public MedicalReport create(@RequestBody MedicalReport medicalReport) {
        return medicalReportRepository.save(medicalReport);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        medicalReportRepository.deleteById(id);
    }

}
