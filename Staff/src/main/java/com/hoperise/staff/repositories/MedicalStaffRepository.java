package com.hoperise.staff.repositories;

import com.hoperise.staff.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalStaffRepository extends JpaRepository<Doctor, Long> {
}
