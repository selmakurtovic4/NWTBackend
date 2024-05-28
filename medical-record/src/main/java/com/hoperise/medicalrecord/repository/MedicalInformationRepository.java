package com.hoperise.medicalrecord.repository;

import com.hoperise.medicalrecord.model.MedicalInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicalInformationRepository extends JpaRepository<MedicalInformation, Long> {
    boolean existsByPatientId(Long id);

    Optional<MedicalInformation> findByPatientId(Long id);
}
