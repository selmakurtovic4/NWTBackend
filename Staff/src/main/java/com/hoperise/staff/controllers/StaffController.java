package com.hoperise.staff.controllers;

import com.hoperise.staff.models.Doctor;
import com.hoperise.staff.models.User;
import com.hoperise.staff.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {

    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }


    @GetMapping("/doctors/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        Doctor doctor = staffService.getDoctorById(id);
        if (doctor != null) {
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/doctors")
    public ResponseEntity<List<Doctor>> getDoctorByLastName(@RequestParam String lastName) {
        List<Doctor> doctors = staffService.getDoctorByLastName(lastName);
        if (!doctors.isEmpty()) {
            return new ResponseEntity<>(doctors, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public void CreateDoctor(User user, int departmentId, String specialization, String biography){
      staffService.createDoctor(user, departmentId, specialization, biography);

    }

}
