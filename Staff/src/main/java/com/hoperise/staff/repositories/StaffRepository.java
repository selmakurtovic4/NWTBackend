package com.hoperise.staff.repositories;

import com.hoperise.staff.models.MedicalStaff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<MedicalStaff, Long> {
}
