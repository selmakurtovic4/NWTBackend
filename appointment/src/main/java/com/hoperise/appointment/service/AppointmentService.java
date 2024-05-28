package com.hoperise.appointment.service;

import com.hoperise.appointment.model.appointment.Appointment;
import com.hoperise.appointment.repository.AppointmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment getAppointment(Long id) {
        return appointmentRepository.findById(id).get();
    }

    public Appointment addAppointment(Appointment appointment) {
        Appointment newAppointment = new Appointment();
        newAppointment.setDate(appointment.getDate());
        newAppointment.setTime(appointment.getTime());
        newAppointment.setStatus(appointment.getStatus());
        newAppointment.setDoctorId(appointment.getDoctorId());
        newAppointment.setPatientId(appointment.getPatientId());
        newAppointment.setCreated(LocalDateTime.now());
        //
        newAppointment.setCreatedBy("CreatedBy");
        //
        appointmentRepository.save(newAppointment);
        return newAppointment;
    }

    public Appointment updateAppointment(Appointment appointment, Long id) {
        var exception = new EntityNotFoundException("Appointment with id " + id + " does not exist!");
        Appointment newAppointment = appointmentRepository.findById(id).orElseThrow(()->exception);
        newAppointment.setDate(appointment.getDate());
        newAppointment.setTime(appointment.getTime());
        newAppointment.setStatus(appointment.getStatus());
        newAppointment.setDoctorId(appointment.getDoctorId());
        newAppointment.setPatientId(appointment.getPatientId());
        newAppointment.setModified(LocalDateTime.now());
        //
        newAppointment.setModifiedBy("ModifiedBy");
        //
        return appointmentRepository.save(newAppointment);
    }

    public String deleteAppointment(Long id) {
        var exception = new EntityNotFoundException("Appointment with id " + id + " does not exist!");
        appointmentRepository.findById(id).orElseThrow(() -> exception);
        appointmentRepository.deleteById(id);
        return "Appointment with id " + id + " is successfully deleted!";
    }
    public List<Appointment> getDoctorAppointments(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    public List<Appointment> getPatientAppointments(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }
}
