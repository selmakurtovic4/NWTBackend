package com.hoperise.staff.services;

import com.hoperise.staff.dtos.ServiceDTO;
import com.hoperise.staff.models.Department;
import com.hoperise.staff.models.MedicalService;
import com.hoperise.staff.repositories.DepartmentRepository;
import com.hoperise.staff.repositories.ServiceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class ServiceServiceTest {

    @Mock
    private ServiceRepository serviceRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private ServiceService serviceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        initializeData();
    }

    private void initializeData() {
        MedicalService service = new MedicalService();
        service.setId(1);
        service.setName("Test Service");
        service.setPrice(100);
        Department department = new Department();
        department.setId(1);
        department.setName("Test Department");
        service.setDepartment(department);

        when(serviceRepository.findById(1L)).thenReturn(Optional.of(service));
        when(departmentRepository.findById((long) department.getId())).thenReturn(Optional.of(department));
    }

    @Test
    public void testCreateService() {
        MedicalService service = new MedicalService();
        serviceService.createService(service);
        verify(serviceRepository, times(1)).save(service);
    }

    @Test
    public void testGetServiceById_ExistingService() {
        int id = 1;

        ServiceDTO result = serviceService.getServiceById(id);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(id, result.getId());
        Assertions.assertEquals("Test Service", result.getName());
        Assertions.assertEquals(100.0, result.getPrice());
        Assertions.assertEquals("Test Department", result.getDepartmentName());
    }

    @Test
    public void testGetServiceById_NonExistingService() {
        int id = 2; // Non-existing ID

        ServiceDTO result = serviceService.getServiceById(id);

        Assertions.assertNull(result);
    }

    @Test
    public void testDeleteService() {
        int id = 1;
        serviceService.deleteService(id);
        verify(serviceRepository, times(1)).deleteById((long) id);
    }

    @Test
    public void testGetServicesByDepartmentId_ExistingDepartment() {
        int departmentId = 1;
        MedicalService service = new MedicalService();
        service.setId(1);
        service.setName("Test Service");
        service.setPrice(100);
        Department department = new Department();
        department.setId(departmentId);
        department.setName("Test Department");
        service.setDepartment(department);

        when(serviceRepository.findByDepartmentId(departmentId)).thenReturn(service);

        MedicalService result = serviceService.getServicesByDepartmentId(departmentId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(service.getId(), result.getId());
        Assertions.assertEquals(service.getName(), result.getName());
        Assertions.assertEquals(service.getPrice(), result.getPrice());
        Assertions.assertEquals(service.getDepartment().getId(), result.getDepartment().getId());
        Assertions.assertEquals(service.getDepartment().getName(), result.getDepartment().getName());
    }

    @Test
    public void testGetServicesByDepartmentId_NonExistingDepartment() {
        int departmentId = 2; // Non-existing ID

        when(serviceRepository.findByDepartmentId(departmentId)).thenReturn(null);

        MedicalService result = serviceService.getServicesByDepartmentId(departmentId);

        Assertions.assertNull(result);
    }

    @Test
    public void testFindByName_ExistingService() {
        String name = "Test Service";
        MedicalService service = new MedicalService();
        service.setId(1);
        service.setName(name);
        service.setPrice(100);
        Department department = new Department();
        department.setId(1);
        department.setName("Test Department");
        service.setDepartment(department);

        when(serviceRepository.findByName(name)).thenReturn(service);

        MedicalService result = serviceService.findByName(name);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(service.getId(), result.getId());
        Assertions.assertEquals(service.getName(), result.getName());
        Assertions.assertEquals(service.getPrice(), result.getPrice());
        Assertions.assertEquals(service.getDepartment().getId(), result.getDepartment().getId());
        Assertions.assertEquals(service.getDepartment().getName(), result.getDepartment().getName());
    }

    @Test
    public void testFindByName_NonExistingService() {
        String name = "Non-existing Service";

        when(serviceRepository.findByName(name)).thenReturn(null);

        MedicalService result = serviceService.findByName(name);

        Assertions.assertNull(result);
    }
}
