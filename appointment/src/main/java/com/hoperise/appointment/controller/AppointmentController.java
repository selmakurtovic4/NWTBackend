package com.hoperise.appointment.controller;

import com.hoperise.appointment.model.appointment.Appointment;
import com.hoperise.appointment.model.appointment.AppointmentRequest;
import com.hoperise.appointment.service.AppointmentService;
import jakarta.validation.Valid;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private RestTemplate restTemplate;

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
    public @ResponseBody ResponseEntity<Appointment> addAppointment(@Valid @RequestBody AppointmentRequest appointment) {
        var newAppointment = appointmentService.addAppointment(appointment);
        return new ResponseEntity<>(newAppointment, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public @ResponseBody ResponseEntity<Appointment> updateAppointment(@Valid @RequestBody AppointmentRequest appointment, @PathVariable Long id) {
        var newAppointment = appointmentService.updateAppointment(appointment, id);
        return new ResponseEntity<>(newAppointment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<Map<String, String>> deleteAppointment(@PathVariable Long id) {
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

    @GetMapping("/getAppsByPatientLastName/{lastName}")
    public ResponseEntity<List<Appointment>> getPatientAppointmentsByLastName(@PathVariable String lastName){
        String patientIdUrl = "http://patient/patient/getIdByLastName/" + lastName;
        Long patientId = restTemplate.getForObject(patientIdUrl, Long.class);

        List<Appointment> appointments = appointmentService.getPatientAppointments(patientId);
        return ResponseEntity.ok(appointments);
    }
}
