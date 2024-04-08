package com.hoperise.medicalrecord.service;

import com.hoperise.medicalrecord.model.MedicalReport;
import com.hoperise.medicalrecord.repository.MedicalReportRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MedicalReportService {
    @Autowired
    private MedicalReportRepository medicalReportRepository;

    public List<MedicalReport> getAllMedicalReports() {
        return medicalReportRepository.findAll();
    }

    public MedicalReport createMedicalReport(MedicalReport medicalReport) {
        MedicalReport newMedicalReport = new MedicalReport();
        newMedicalReport.setDiagnosis(medicalReport.getDiagnosis());
        newMedicalReport.setTreatment_plan(medicalReport.getTreatment_plan());
        newMedicalReport.setTests_results(medicalReport.getTests_results());
        newMedicalReport.setAdditional_notes(medicalReport.getAdditional_notes());
        newMedicalReport.setDate(medicalReport.getDate());
        newMedicalReport.setPatientId(medicalReport.getPatientId());
        newMedicalReport.setDoctorId(medicalReport.getDoctorId());
        newMedicalReport.setCreated(LocalDateTime.now());
        newMedicalReport.setCreatedBy("Amina");

        return medicalReportRepository.save(newMedicalReport);
    }

    public String deleteMedicalReport(Long id) {
        if (medicalReportRepository.existsById(id)) {
            medicalReportRepository.deleteById(id);
            return "Medical report deleted successfully";
        } else {
            return "Medical report with that ID doesn't exist";
        }
    }
}
