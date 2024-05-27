package com.hoperise.staff.repositories;

import com.hoperise.staff.models.StaffMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NonMedicalStaffRepository extends JpaRepository<StaffMember, Long> {
}
