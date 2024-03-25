package com.hoperise.medicalrecord;

import com.hoperise.medicalrecord.model.MedicalInformation;
import com.hoperise.medicalrecord.model.MedicalReport;
import com.hoperise.medicalrecord.repository.MedicalInformationRepository;
import com.hoperise.medicalrecord.repository.MedicalReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@SpringBootApplication
public class MedicalRecordApplication implements CommandLineRunner {
	@Autowired
	MedicalReportRepository medicalReportRepository;

	@Autowired
	MedicalInformationRepository medicalInformationRepository;

	public static void main(String[] args) {
		SpringApplication.run(MedicalRecordApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		medicalReportRepository.deleteAll();
		medicalInformationRepository.deleteAll();

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
	}
}
