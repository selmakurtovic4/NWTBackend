package com.hoperise.staff;

import com.hoperise.staff.dtos.DepartmentDTO;
import com.hoperise.staff.models.Department;
import com.hoperise.staff.services.IDepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DepartmentControllerTest {

    @Mock
    private IDepartmentService departmentService;

    @InjectMocks
    private com.hoperise.staff.controller.DepartmentController departmentController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllDepartments() {
        List<DepartmentDTO> departments = Arrays.asList(
                new DepartmentDTO(1, "Department 1"),
                new DepartmentDTO(2, "Department 2")
        );
        when(departmentService.getAllDepartments()).thenReturn(departments);

        ResponseEntity<List<DepartmentDTO>> response = departmentController.getAllDepartments();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(departments, response.getBody());
    }

    @Test
    public void testGetDepartment() {
        int id = 1;
        Department department = new Department(id, "Department 1");
        when(departmentService.getDepartmentById(id)).thenReturn(department);

        ResponseEntity<Department> response = departmentController.getDepartment(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(department, response.getBody());
    }

    @Test
    public void testAddDepartment() {
        DepartmentDTO departmentDTO = new DepartmentDTO(1,"New Department");

        ResponseEntity<Void> response = departmentController.addDepartment(departmentDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(departmentService, times(1)).createDepartment(departmentDTO);
    }

    @Test
    public void testUpdateDepartment() {
        int id = 1;
        DepartmentDTO departmentDTO = new DepartmentDTO(1,"Updated Department");

        ResponseEntity<Void> response = departmentController.updateDepartment(departmentDTO, id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(departmentService, times(1)).editDepartment(departmentDTO);
    }

    @Test
    public void testDeleteDepartment() {
        int id = 1;

        ResponseEntity<Void> response = departmentController.deleteDepartment(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(departmentService, times(1)).deleteDepartment(id);
    }
}
