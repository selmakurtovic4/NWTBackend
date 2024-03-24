package com.hoperise.appointment.controller;

import com.hoperise.appointment.model.Appointment;
import com.hoperise.appointment.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private final AppointmentRepository appointmentRepository;

    public AppointmentController(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @GetMapping(path = "/all")
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @PostMapping("/create")
    public Appointment create(@RequestBody Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        appointmentRepository.deleteById(id);
    }

}
