package com.hoperise.staff.controllers;

import com.hoperise.staff.models.Doctor;
import com.hoperise.staff.services.StaffService;
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

public class StaffControllerTest {

    @Mock
    private StaffService staffService;

    @InjectMocks
    private StaffController staffController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetDoctorById_ExistingDoctor() {
        Long id = 1L;
        Doctor doctor = new Doctor();
        when(staffService.getDoctorById(id)).thenReturn(doctor);

        ResponseEntity<Doctor> response = staffController.getDoctorById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(doctor, response.getBody());
    }

    @Test
    public void testGetDoctorById_NonExistingDoctor() {
        Long id = 1L;
        when(staffService.getDoctorById(id)).thenReturn(null);

        ResponseEntity<Doctor> response = staffController.getDoctorById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetDoctorByLastName_ExistingDoctors() {
        String lastName = "Doe";
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor());
        when(staffService.getDoctorByLastName(lastName)).thenReturn(doctors);

        ResponseEntity<List<Doctor>> response = staffController.getDoctorByLastName(lastName);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(doctors, response.getBody());
    }

    @Test
    public void testGetDoctorByLastName_NoDoctorsFound() {
        String lastName = "Doe";
        when(staffService.getDoctorByLastName(lastName)).thenReturn(new ArrayList<>());

        ResponseEntity<List<Doctor>> response = staffController.getDoctorByLastName(lastName);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
