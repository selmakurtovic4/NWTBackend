package com.hoperise.patient.repository;

import com.hoperise.patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Patient findPatientByUserId(Long userId);

    List<Patient> findByLastName(String lastname);
}


