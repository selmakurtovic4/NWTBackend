package com.hoperise.staff.services;

import com.hoperise.staff.dtos.DepartmentDTO;
import com.hoperise.staff.models.Department;
import com.hoperise.staff.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService implements IDepartmentService{

    @Autowired
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department getDepartmentById(long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    public Department getDepartmentByName(String name){
        return departmentRepository.findByName(name);

    }
    @Override
    public void createDepartment(DepartmentDTO departmentDTO) {
        Department department=new Department(departmentDTO.getName());
        departmentRepository.save(department);
    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepository.findAll().stream().map(department -> new DepartmentDTO(department.getId(), department.getName())).toList();
    }

    @Override
    public void editDepartment(DepartmentDTO department) {
        // Assuming departmentRepository has a method findById(int id)
        Department existingDepartment = departmentRepository.findById((long)department.getId()).orElse(null);
        if (existingDepartment != null) {
            existingDepartment.setName(department.getName());
            departmentRepository.save(existingDepartment);
        }
    }


    @Override
    public void deleteDepartment(long id) {
        departmentRepository.deleteById(id);
    }
}
