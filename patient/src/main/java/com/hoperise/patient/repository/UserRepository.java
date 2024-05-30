package com.hoperise.patient.repository;

import com.hoperise.patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Patient, Long> {
}
