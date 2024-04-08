package com.hoperise.medicalrecord.repository;

import com.hoperise.medicalrecord.model.MedicalInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalInformationRepository extends JpaRepository<MedicalInformation, Long> {
    boolean existsByPatientId(Long patientId);
}
