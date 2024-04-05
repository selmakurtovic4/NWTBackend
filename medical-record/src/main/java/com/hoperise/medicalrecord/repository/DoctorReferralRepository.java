package com.hoperise.medicalrecord.repository;

import com.hoperise.medicalrecord.model.DoctorReferral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DoctorReferralRepository extends JpaRepository<DoctorReferral, Long> {
    @Query("SELECT dr FROM DoctorReferral dr WHERE dr.referringDoctorId = :referringDoctorId")
    List<DoctorReferral> findByReferringDoctorId(@Param("referringDoctorId") Long referringDoctorId);
    @Query("SELECT dr FROM DoctorReferral dr WHERE dr.patientId = :patientId")
    List<DoctorReferral> findByPatientId(@Param("patientId") Long patientId);
    @Query("SELECT dr FROM DoctorReferral dr WHERE dr.referredDoctorId = :referredDoctorId")
    List<DoctorReferral> findByReferredDoctorId(@Param("referredDoctorId") Long referredDoctorId);
}
