package com.hoperise.medicalrecord.repository;

import com.hoperise.medicalrecord.model.MedicalReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalReportRepository extends JpaRepository<MedicalReport, Long> {
}
