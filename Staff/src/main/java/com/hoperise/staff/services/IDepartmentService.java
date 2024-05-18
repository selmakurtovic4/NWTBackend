package com.hoperise.staff.services;

import com.hoperise.staff.dtos.DepartmentDTO;
import com.hoperise.staff.models.Department;

import java.util.List;

public interface IDepartmentService {
    Department getDepartmentById(long id);

    void editDepartment(DepartmentDTO department);
    void deleteDepartment(long id);

    void createDepartment(DepartmentDTO departmentDTO);

    List<DepartmentDTO> getAllDepartments();
}
