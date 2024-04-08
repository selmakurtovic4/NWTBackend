package com.hoperise.appointment.controller;

import com.hoperise.appointment.model.appointment.Appointment;
import com.hoperise.appointment.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/all")
    public @ResponseBody ResponseEntity<List<Appointment>> getAllAppointments() {
        var appointments = appointmentService.getAllAppointments();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<Appointment> getAppointment(@PathVariable Long id) {
        var appointment = appointmentService.getAppointment(id);
        return  new ResponseEntity<>(appointment, HttpStatus.OK);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody ResponseEntity<Appointment> addAppointment(@Valid @RequestBody Appointment appointment) {
        var newAppointment = appointmentService.addAppointment(appointment);
        return new ResponseEntity<>(newAppointment, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public @ResponseBody ResponseEntity<Appointment> updateAppointment(@Valid @RequestBody Appointment appointment, @PathVariable Long id) {
        var newAppointment = appointmentService.updateAppointment(appointment, id);
        return new ResponseEntity<>(newAppointment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<String> deleteAppointment(@PathVariable Long id) {
        return new ResponseEntity<>(appointmentService.deleteAppointment(id), HttpStatus.OK);
    }

    @GetMapping("/getAppsByDid/{doctor_id}")
    public ResponseEntity<List<Appointment>> getDoctorAppointments(@PathVariable Long doctor_id){
        var appointments = appointmentService.getDoctorAppointments(doctor_id);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @GetMapping("/getAppsByPid/{patient_id}")
    public ResponseEntity<List<Appointment>> getPatientAppointments(@PathVariable Long patient_id){
        var appointments = appointmentService.getPatientAppointments(patient_id);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

}
