package com.hoperise.staff.repositories;

import com.hoperise.staff.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
