package com.hoperise.medicalrecord.repository;

import com.hoperise.medicalrecord.model.MedicalInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalInformationRepository extends JpaRepository<MedicalInformation, Long> {
    boolean existsByPatientId(Long patientId);
}
