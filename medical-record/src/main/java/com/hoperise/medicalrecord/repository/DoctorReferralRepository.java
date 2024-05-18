package com.hoperise.medicalrecord.repository;

import com.hoperise.medicalrecord.model.DoctorReferral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DoctorReferralRepository extends JpaRepository<DoctorReferral, Long> {
    @Query("SELECT dr FROM DoctorReferral dr WHERE dr.referringDoctorId = :referringDoctorId")
    List<DoctorReferral> findByReferringDoctorId(@Param("referringDoctorId") Long referringDoctorId);
    @Query("SELECT dr FROM DoctorReferral dr WHERE dr.patientId = :patientId")
    List<DoctorReferral> findByPatientId(@Param("patientId") Long patientId);
    @Query("SELECT dr FROM DoctorReferral dr WHERE dr.referredDoctorId = :referredDoctorId")
    List<DoctorReferral> findByReferredDoctorId(@Param("referredDoctorId") Long referredDoctorId);
    @Query("SELECT CASE WHEN COUNT(dr) > 0 THEN true ELSE false END FROM DoctorReferral dr WHERE dr.date = :date AND dr.patientId = :patientId")
    boolean existsByDateAndPatientId(@Param("date") LocalDateTime date, @Param("patientId") Long patientId);
}
