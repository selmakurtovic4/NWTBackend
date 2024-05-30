package com.hoperise.staff.repositories;

import com.hoperise.staff.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalStaffRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> findByDepartmentId(Long departmentId);
    List<Doctor> findByLastName(String lastName);
}
