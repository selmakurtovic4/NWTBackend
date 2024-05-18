package com.hoperise.staff.services;

import com.hoperise.staff.dtos.ServiceDTO;
import com.hoperise.staff.models.MedicalService;

import java.util.List;

public interface IServiceService {
    // Create a new service
    MedicalService createService(MedicalService service);

    // Retrieve a service by its unique identifier
    ServiceDTO getServiceById(long id);

    // Update an existing service
    MedicalService updateService(MedicalService service);

    // Delete a service
    void deleteService(long id);

    // Retrieve services by department ID
    List<MedicalService> getServicesByDepartmentId(long departmentId);
}
