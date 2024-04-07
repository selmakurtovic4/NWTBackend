package com.hoperise.appointment.model.appointment;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hoperise.appointment.model.Review;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "date")
    @NotNull(message = "Date must be specified!")
    private LocalDate date;
    @Column(name = "time")
    @NotNull(message = "Time must be specified!")
    private LocalTime time;
    @Column(name = "status")
    @NotNull(message = "Status must be specified!")
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;
    @Column(name = "patient_id")
    private Long patientId;
    @Column(name = "doctor_id")
    @NotNull(message = "Doctor ID must be specified!")
    private Long doctorId;
    @Column(name="created")
    @NotNull(message = "Creation date must be specified!")
    private LocalDateTime created;
    @Column(name = "created_by")
    @NotNull(message ="User that created the appointment must be specified!")
    private String createdBy;
    @Column(name = "modified")
    private LocalDateTime modified;
    @Column(name = "modified_by")
    private String modifiedBy;
    @OneToOne(mappedBy = "appointment")
    @JsonIgnore
    private Review review;

    public Appointment() {
    }

    public Appointment(LocalDate date, LocalTime time, AppointmentStatus status, Long patientId, Long doctorId, LocalDateTime created, String createdBy, LocalDateTime modified, String modifiedBy) {
        this.date = date;
        this.time = time;
        this.status = status;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.created = created;
        this.createdBy = createdBy;
        this.modified = modified;
        this.modifiedBy = modifiedBy;
    }

    public Appointment(LocalDate date, LocalTime time, AppointmentStatus status, Long patientId, Long doctorId, LocalDateTime created, String createdBy) {
        this.date = date;
        this.time = time;
        this.status = status;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.created = created;
        this.createdBy = createdBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", date=" + date +
                ", time=" + time +
                ", status='" + status + '\'' +
                ", patientId=" + patientId +
                ", doctorId=" + doctorId +
                ", created=" + created +
                ", createdBy='" + createdBy + '\'' +
                ", modified=" + modified +
                ", modifiedBy='" + modifiedBy + '\'' +
                '}';
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

}
