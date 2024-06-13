package com.hoperise.staff.controllers;

import com.hoperise.staff.dtos.ServiceDTO;
import com.hoperise.staff.models.MedicalService;
import com.hoperise.staff.services.IServiceService;
import com.hoperise.staff.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service")
public class ServiceController {

    private final ServiceService serviceService;

    @Autowired
    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceDTO> getService(@PathVariable long id) {
        ServiceDTO serviceDTO = serviceService.getServiceById(id);
        if (serviceDTO != null) {
            return new ResponseEntity<>(serviceDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addService(@RequestBody MedicalService service) {
        MedicalService createdService = serviceService.createService(service);
        if (createdService != null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable long id) {
        serviceService.deleteService(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<MedicalService>> getServicesByDepartmentId(@PathVariable int departmentId) {
        List<MedicalService> services = serviceService.getServicesByDepartmentId(departmentId);
        if (!services.isEmpty()) {
            return new ResponseEntity<>(services, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<MedicalService>> getAllServices() {
        List<MedicalService> services = serviceService.getAllServices();
        if (!services.isEmpty()) {
            return new ResponseEntity<>(services, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
