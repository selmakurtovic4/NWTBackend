package com.hoperise.medicalrecord.service;

import com.hoperise.medicalrecord.model.MedicalReport;
import com.hoperise.medicalrecord.repository.MedicalReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MedicalReportService {
    @Autowired
    private MedicalReportRepository medicalReportRepository;

    public List<MedicalReport> getAllMedicalReports() {
        return medicalReportRepository.findAll();
    }

    public Optional<MedicalReport> getMedicalReport(Long id) {
        return medicalReportRepository.findById(id);
    }

    public Optional<MedicalReport> getMedicalReportsForPatient(Long id) {
        return medicalReportRepository.findByPatientId(id);
    }

    public MedicalReport createMedicalReport(MedicalReport medicalReport) {
        if (medicalReportRepository.existsByDateAndPatientId(medicalReport.getDate(), medicalReport.getPatientId())) {
            return new MedicalReport();
        }

        MedicalReport newMedicalReport = new MedicalReport();
        newMedicalReport.setDiagnosis(medicalReport.getDiagnosis());
        newMedicalReport.setTreatmentPlan(medicalReport.getTreatmentPlan());
        newMedicalReport.setTestsResults(medicalReport.getTestsResults());
        newMedicalReport.setAdditionalNotes(medicalReport.getAdditionalNotes());
        newMedicalReport.setDate(medicalReport.getDate());
        newMedicalReport.setPatientId(medicalReport.getPatientId());
        newMedicalReport.setDoctorId(medicalReport.getDoctorId());
        newMedicalReport.setCreated(LocalDateTime.now());
        newMedicalReport.setCreatedBy("Amina");

        return medicalReportRepository.save(newMedicalReport);
    }

    public boolean deleteMedicalReport(Long id) {
        if (medicalReportRepository.existsById(id)) {
            medicalReportRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
