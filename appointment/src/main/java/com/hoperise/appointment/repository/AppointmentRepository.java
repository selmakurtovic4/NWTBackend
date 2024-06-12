package com.hoperise.appointment.repository;

import com.hoperise.appointment.model.appointment.Appointment;
import com.hoperise.appointment.model.appointment.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByDoctorId(Long doctorId);
    List<Appointment> findByPatientId(Long patientId);

    List<Appointment> findByDoctorIdAndDateAfter(Long doctorId, LocalDate date);
    List<Appointment> findByDoctorIdAndDateBefore(Long doctorId, LocalDate date);
    List<Appointment> findByDoctorIdAndDate(Long doctorId, LocalDate date);
    List<Appointment> findByPatientIdAndDateAfter(Long patientId, LocalDate date);
    List<Appointment> findByPatientIdAndDateBefore(Long patientId, LocalDate date);
    List<Appointment> findByPatientIdAndDate(Long patientId, LocalDate date);

    List<Appointment> findByDoctorIdAndDateAndStatus(Long doctorId, LocalDate date, AppointmentStatus status);

}
