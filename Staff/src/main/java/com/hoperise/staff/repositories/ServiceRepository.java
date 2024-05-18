package com.hoperise.staff.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hoperise.staff.models.MedicalService; // Assuming Service model is defined elsewhere

public interface ServiceRepository extends JpaRepository<MedicalService, Long> {

}
