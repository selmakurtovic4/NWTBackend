package com.hoperise.appointment.service;

import com.hoperise.appointment.model.appointment.Appointment;
import com.hoperise.appointment.model.appointment.AppointmentRequest;
import com.hoperise.appointment.model.appointment.AppointmentStatus;
import com.hoperise.appointment.repository.AppointmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment getAppointment(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Appointment with ID " + id + " not found"));
    }

    public Appointment addAppointment(AppointmentRequest appointment) {
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

    public Appointment updateAppointment(AppointmentRequest appointment, Long id) {
        Appointment existingAppointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Appointment with ID " + id + " does not exist"));

        existingAppointment.setDate(appointment.getDate());
        existingAppointment.setTime(appointment.getTime());
        existingAppointment.setStatus(appointment.getStatus());
        existingAppointment.setDoctorId(appointment.getDoctorId());
        existingAppointment.setPatientId(appointment.getPatientId());
        existingAppointment.setModified(LocalDateTime.now());
        //
        existingAppointment.setModifiedBy("ModifiedBy");
        //
        return appointmentRepository.save(existingAppointment);
    }

    public Map<String, String> deleteAppointment(Long id) {
        appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Appointment with ID " + id + " does not exist"));
        appointmentRepository.deleteById(id);
        return Collections.singletonMap("message", "Appointment with ID " + id + " is successfully deleted!");
    }

    public List<Appointment> getDoctorAppointments(Long doctorId) {
        List<Appointment> appointments = appointmentRepository.findByDoctorId(doctorId);
        if (appointments.isEmpty()) {
            throw new EntityNotFoundException("No appointments found for doctor with ID " + doctorId);
        }
        return appointments;
    }

    public List<Appointment> getPatientAppointments(Long patientId) {
        List<Appointment> appointments = appointmentRepository.findByPatientId(patientId);
        if (appointments.isEmpty()) {
            throw new EntityNotFoundException("No appointments found for patient with ID " + patientId);
        }
        return appointments;
    }

    public List<Appointment> getUpcomingAppointmentsForDoctor(Long doctorId) {
        return appointmentRepository.findByDoctorIdAndDateAfter(doctorId, LocalDate.now());
    }

    public List<Appointment> getPastAppointmentsForDoctor(Long doctorId) {
        return appointmentRepository.findByDoctorIdAndDateBefore(doctorId, LocalDate.now());
    }

    public List<Appointment> getTodayAppointmentsForDoctor(Long doctorId) {
        return appointmentRepository.findByDoctorIdAndDate(doctorId, LocalDate.now());
    }

    public List<Appointment> getUpcomingAppointmentsForPatient(Long patientId) {
        return appointmentRepository.findByPatientIdAndDateAfter(patientId, LocalDate.now());
    }

    public List<Appointment> getPastAppointmentsForPatient(Long patientId) {
        return appointmentRepository.findByPatientIdAndDateBefore(patientId, LocalDate.now());
    }

    public List<Appointment> getTodayAppointmentsForPatient(Long patientId) {
        return appointmentRepository.findByPatientIdAndDate(patientId, LocalDate.now());
    }

    public List<Appointment> getAvailableAppointmentsForDoctorAndDate(Long doctorId, LocalDate date) {
        return appointmentRepository.findByDoctorIdAndDateAndStatus(doctorId, date, AppointmentStatus.AVAILABLE);
    }

    public Appointment bookAppointment(Long appointmentId, Long patientId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new EntityNotFoundException("Appointment with ID " + appointmentId + " not found"));

        if (appointment.getStatus() == AppointmentStatus.AVAILABLE) {
            appointment.setStatus(AppointmentStatus.BOOKED);
            appointment.setPatientId(patientId);
            return appointmentRepository.save(appointment);
        } else {
            throw new IllegalStateException("Appointment with ID " + appointmentId + " is not available for booking.");
        }
    }
}
