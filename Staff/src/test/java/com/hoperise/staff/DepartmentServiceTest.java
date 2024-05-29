package com.hoperise.staff;

import com.hoperise.staff.dtos.DepartmentDTO;
import com.hoperise.staff.models.Department;
import com.hoperise.staff.repositories.DepartmentRepository;
import com.hoperise.staff.services.DepartmentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        initializeData();
    }

    private void initializeData() {
        // Initialize mock data
        List<Department> departments = Arrays.asList(
                new Department(1, "Department 1"),
                new Department(2, "Department 2")
        );

        // Mock behavior for repository methods
        when(departmentRepository.findById(1L)).thenReturn(Optional.of(departments.get(0)));
        when(departmentRepository.findById(2L)).thenReturn(Optional.of(departments.get(1)));
        when(departmentRepository.findAll()).thenReturn(departments);
        when(departmentRepository.save(any(Department.class))).thenAnswer(invocation -> {
            Department department = invocation.getArgument(0);
         //   if (department.getId()=) {
                department.setId( (departments.size() + 1));
           // }
            departments.add(department);
            return department;
        });
        doNothing().when(departmentRepository).deleteById(anyLong());
    }

    @Test
    public void testGetDepartmentById() {
        long id = 1L;

        Department result = departmentService.getDepartmentById(id);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(id, result.getId());
    }
    @Test
    public void testGetDepartmentByName() {
        String name = "Department 1";
        Department department = new Department(1, name);
        when(departmentRepository.findByName(name)).thenReturn(department);

        Department result = departmentService.getDepartmentByName(name);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(name, result.getName());
    }

    @Test
    public void testCreateDepartment() {
        DepartmentDTO departmentDTO = new DepartmentDTO(3,"New Department");
        Department department = new Department(3, departmentDTO.getName());
        when(departmentRepository.save(any(Department.class))).thenReturn(department);

        departmentService.createDepartment(departmentDTO);

        verify(departmentRepository, times(1)).save(any(Department.class));
    }

    @Test
    public void testGetAllDepartments() {
        List<Department> departments = Arrays.asList(
                new Department(1, "Department 1"),
                new Department(2, "Department 2"),
                new Department(3, "Department 3")
        );
        when(departmentRepository.findAll()).thenReturn(departments);

        List<DepartmentDTO> result = departmentService.getAllDepartments();

        Assertions.assertEquals(departments.size(), result.size());
    }

    @Test
    public void testEditDepartment() {
        DepartmentDTO departmentDTO = new DepartmentDTO(1, "Updated Department");
        Department existingDepartment = new Department(1, "Department 1");
        when(departmentRepository.findById(1L)).thenReturn(Optional.of(existingDepartment));
        when(departmentRepository.save(any(Department.class))).thenReturn(existingDepartment);

        departmentService.editDepartment(departmentDTO);

        verify(departmentRepository, times(1)).save(any(Department.class));
    }

    @Test
    public void testDeleteDepartment() {
        long id = 1L;
        departmentService.deleteDepartment(id);

        verify(departmentRepository, times(1)).deleteById(id);
    }


}
