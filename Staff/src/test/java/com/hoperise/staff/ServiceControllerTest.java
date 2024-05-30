package com.hoperise.staff;

import com.hoperise.staff.controllers.ServiceController;
import com.hoperise.staff.dtos.ServiceDTO;
import com.hoperise.staff.models.Department;
import com.hoperise.staff.models.MedicalService;
import com.hoperise.staff.services.IServiceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ServiceControllerTest {

    @Mock
    private IServiceService serviceService;

    @InjectMocks
    private ServiceController serviceController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetService_ExistingService() {
        long id = 1L;
        ServiceDTO serviceDTO = new ServiceDTO((int) id, "Test Service", 100, "Test Department");

        when(serviceService.getServiceById(id)).thenReturn(serviceDTO);

        ResponseEntity<ServiceDTO> response = serviceController.getService(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(serviceDTO, response.getBody());
    }

    @Test
    public void testGetService_NonExistingService() {
        long id = 1L;

        when(serviceService.getServiceById(id)).thenReturn(null);

        ResponseEntity<ServiceDTO> response = serviceController.getService(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAddService_ValidService() {
        MedicalService service = new MedicalService();
        service.setId(1);

        when(serviceService.createService(service)).thenReturn(service);

        ResponseEntity<Void> response = serviceController.addService(service);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testAddService_InvalidService() {
        MedicalService service = new MedicalService();

        when(serviceService.createService(service)).thenReturn(null);

        ResponseEntity<Void> response = serviceController.addService(service);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testDeleteService() {
        long id = 1L;

        ResponseEntity<Void> response = serviceController.deleteService(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(serviceService, times(1)).deleteService(id);
    }

    @Test
    public void testGetServicesByDepartmentId_ExistingServices() {
        long departmentId = 1L;
        List<MedicalService> services = new ArrayList<>();
        services.add(new MedicalService());
        services.add(new MedicalService());

        when(serviceService.getServicesByDepartmentId(departmentId)).thenReturn(services);

        ResponseEntity<List<MedicalService>> response = serviceController.getServicesByDepartmentId(departmentId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(services, response.getBody());
    }

    @Test
    public void testGetServicesByDepartmentId_NoServicesFound() {
        long departmentId = 1L;

        when(serviceService.getServicesByDepartmentId(departmentId)).thenReturn(new ArrayList<>());

        ResponseEntity<List<MedicalService>> response = serviceController.getServicesByDepartmentId(departmentId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
