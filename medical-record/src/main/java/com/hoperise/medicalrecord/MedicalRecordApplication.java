package com.hoperise.medicalrecord;

import com.hoperise.medicalrecord.model.DoctorReferral;
import com.hoperise.medicalrecord.model.MedicalInformation;
import com.hoperise.medicalrecord.model.MedicalReport;
import com.hoperise.medicalrecord.model.Priority;
import com.hoperise.medicalrecord.repository.DoctorReferralRepository;
import com.hoperise.medicalrecord.repository.MedicalInformationRepository;
import com.hoperise.medicalrecord.repository.MedicalReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

@SpringBootApplication
public class MedicalRecordApplication implements CommandLineRunner {
    @Autowired
    MedicalReportRepository medicalReportRepository;

    @Autowired
    MedicalInformationRepository medicalInformationRepository;

    @Autowired
    DoctorReferralRepository doctorReferralRepository;

    public static void main(String[] args) {
        SpringApplication.run(MedicalRecordApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        medicalReportRepository.deleteAll();
        medicalInformationRepository.deleteAll();
        doctorReferralRepository.deleteAll();

        var creationDate = LocalDateTime.now();
        var createdBy = "Amina";
        var reportDate = LocalDate.now();

        var medicalReports = new ArrayList<MedicalReport>();
        medicalReports.add(new MedicalReport("Diagnosis", "Treatment plan", "Tests results", null, reportDate, 1L, 1L, creationDate, createdBy));
        medicalReports.add(new MedicalReport("Diagnosis1", "Treatment plan", "Tests results", null, reportDate, 2L, 1L, creationDate, createdBy));
        medicalReports.add(new MedicalReport("Diagnosis2", "Treatment plan", "Tests results", null, reportDate, 3L, 1L, creationDate, createdBy));
        medicalReports.add(new MedicalReport("Diagnosis3", "Treatment plan", "Tests results", null, reportDate, 4L, 1L, creationDate, createdBy));
        medicalReports.add(new MedicalReport("Diagnosis4", "Treatment plan", "Tests results", null, reportDate, 5L, 1L, creationDate, createdBy));
        medicalReports.add(new MedicalReport("Diagnosis5", "Treatment plan", "Tests results", null, reportDate, 1L, 2L, creationDate, createdBy));
        medicalReports.add(new MedicalReport("Diagnosis6", "Treatment plan", "Tests results", null, reportDate, 1L, 3L, creationDate, createdBy));
        medicalReports.add(new MedicalReport("Diagnosis7", "Treatment plan", "Tests results", null, reportDate, 1L, 4L, creationDate, createdBy));
        medicalReports.add(new MedicalReport("Diagnosis8", "Treatment plan", "Tests results", null, reportDate, 2L, 2L, creationDate, createdBy));
        medicalReports.add(new MedicalReport("Diagnosis9", "Treatment plan", "Tests results", null, reportDate, 2L, 3L, creationDate, createdBy));
        medicalReports.add(new MedicalReport("Diagnosis10", "Treatment plan", "Tests results", null, reportDate, 2L, 4L, creationDate, createdBy));
        medicalReportRepository.saveAll(medicalReports);

        var medicalInformation = new ArrayList<MedicalInformation>();
        medicalInformation.add(new MedicalInformation(null, null, null, null, 180, 80, 1L, creationDate, createdBy));
        medicalInformation.add(new MedicalInformation(null, null, null, null, 180, 80, 2L, creationDate, createdBy));
        medicalInformation.add(new MedicalInformation(null, null, null, null, 180, 80, 3L, creationDate, createdBy));
        medicalInformation.add(new MedicalInformation(null, null, null, null, 180, 80, 4L, creationDate, createdBy));
        medicalInformation.add(new MedicalInformation(null, null, null, null, 180, 80, 5L, creationDate, createdBy));
        medicalInformation.add(new MedicalInformation(null, null, null, null, 180, 80, 6L, creationDate, createdBy));
        medicalInformation.add(new MedicalInformation(null, null, null, null, 180, 80, 7L, creationDate, createdBy));
        medicalInformationRepository.saveAll(medicalInformation);

        var doctorReferrals = new ArrayList<DoctorReferral>();
        doctorReferrals.add(new DoctorReferral("Check up", Priority.NON_URGENT, LocalDate.of(2024, 5, 21), 1L, 1L, 2L, creationDate, createdBy));
        doctorReferrals.add(new DoctorReferral("Check up", Priority.NON_URGENT, LocalDate.of(2024, 6, 21), 2L, 2L, 1L, creationDate, createdBy));
        doctorReferrals.add(new DoctorReferral("CT scan", Priority.URGENT, LocalDate.of(2024, 5, 21), 3L, 3L, 2L, creationDate, createdBy));
        doctorReferrals.add(new DoctorReferral("Surgery", Priority.VERY_URGENT, LocalDate.of(2024, 7, 10), 1L, 4L, 3L, creationDate, createdBy));
        doctorReferrals.add(new DoctorReferral("Biopsy", Priority.VERY_URGENT, LocalDate.of(2024, 8, 10), 2L, 1L, 1L, creationDate, createdBy));
        doctorReferrals.add(new DoctorReferral("Dentist", Priority.URGENT, LocalDate.of(2024, 9, 21), 3L, 2L, 2L, creationDate, createdBy));
        doctorReferrals.add(new DoctorReferral("Eye check up", Priority.NON_URGENT, LocalDate.of(2024, 5, 21), 1L, 3L, 3L, creationDate, createdBy));
        doctorReferrals.add(new DoctorReferral("Heart scan", Priority.URGENT, LocalDate.of(2024, 6, 10), 2L, 4L, 1L, creationDate, createdBy));
        doctorReferrals.add(new DoctorReferral("Lung scan", Priority.URGENT, LocalDate.of(2024, 7, 10), 3L, 5L, 2L, creationDate, createdBy));
        doctorReferrals.add(new DoctorReferral("Sewing up patient's arm", Priority.VERY_URGENT, LocalDate.of(2024, 8, 21), 1L, 1L, 3L, creationDate, createdBy));
        doctorReferralRepository.saveAll(doctorReferrals);
    }
}
