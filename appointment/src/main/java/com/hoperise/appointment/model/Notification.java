package com.hoperise.appointment.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @NotNull
    @Column(name = "message")
    private String message;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(message = "Appointment must be specified!")
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;
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

    public Notification() {
    }

    public Notification(String message, Appointment appointment, LocalDateTime created, String createdBy, LocalDateTime modified, String modifiedBy) {
        this.message = message;
        this.appointment = appointment;
        this.created = created;
        this.createdBy = createdBy;
        this.modified = modified;
        this.modifiedBy = modifiedBy;
    }
    public Notification(String message, Appointment appointment, LocalDateTime created, String createdBy) {
        this.message = message;
        this.appointment = appointment;
        this.created = created;
        this.createdBy = createdBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
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
}
