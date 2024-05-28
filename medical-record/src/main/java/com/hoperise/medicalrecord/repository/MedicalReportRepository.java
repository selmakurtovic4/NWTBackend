package com.hoperise.medicalrecord.repository;

import com.hoperise.medicalrecord.model.MedicalReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface MedicalReportRepository extends JpaRepository<MedicalReport, Long> {
    @Query("SELECT CASE WHEN COUNT(mr) > 0 THEN true ELSE false END FROM MedicalReport mr WHERE mr.date = :date AND mr.patientId = :patientId")
    boolean existsByDateAndPatientId(@Param("date") LocalDate date, @Param("patientId") Long patientId);

    Optional<MedicalReport> findByPatientId(Long id);
}
