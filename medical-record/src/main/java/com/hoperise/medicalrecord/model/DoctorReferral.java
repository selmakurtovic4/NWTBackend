package com.hoperise.medicalrecord.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "doctor-referral")
public class DoctorReferral {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "reason")
    @NotNull(message = "Reason can't be null!")
    private String reason;
    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Priority can't be null!")
    private Priority priority;
    @Column(name = "date")
    @NotNull(message = "Date can't be null!")
    private LocalDateTime date;
    @Column(name = "referring_doctor_id")
    @NotNull(message = "Referring doctor ID can't be null!")
    private Long referringDoctorId;
    @Column(name = "patient_id")
    @NotNull(message = "Patient ID can't be null!")
    private Long patientId;
    @Column(name = "referred_doctor_id")
    @NotNull(message = "Referred doctor ID can't be null!")
    private Long referredDoctorId;
    @Column(name = "created")
    @NotNull(message = "Creation date must be specified!")
    private LocalDateTime created;
    @Column(name = "created_by")
    @NotNull(message = "User that created the appointment must be specified!")
    private String createdBy;
    @Column(name = "modified")
    private LocalDateTime modified;
    @Column(name = "modified_by")
    private String modifiedBy;

    public DoctorReferral() {
    }

    public DoctorReferral(String reason, Priority priority, LocalDateTime date, Long referringDoctorId, Long patientId, Long referredDoctorId, LocalDateTime created, String createdBy, LocalDateTime modified, String modifiedBy) {
        this.reason = reason;
        this.priority = priority;
        this.date = date;
        this.referringDoctorId = referringDoctorId;
        this.patientId = patientId;
        this.referredDoctorId = referredDoctorId;
        this.created = created;
        this.createdBy = createdBy;
        this.modified = modified;
        this.modifiedBy = modifiedBy;
    }

    public DoctorReferral(String reason, Priority priority, LocalDateTime date, Long referringDoctorId, Long patientId, Long referredDoctorId, LocalDateTime created, String createdBy) {
        this.reason = reason;
        this.priority = priority;
        this.date = date;
        this.referringDoctorId = referringDoctorId;
        this.patientId = patientId;
        this.referredDoctorId = referredDoctorId;
        this.created = created;
        this.createdBy = createdBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getReferringDoctorId() {
        return referringDoctorId;
    }

    public void setReferringDoctorId(Long referringDoctorId) {
        this.referringDoctorId = referringDoctorId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getReferredDoctorId() {
        return referredDoctorId;
    }

    public void setReferredDoctorId(Long referredDoctorId) {
        this.referredDoctorId = referredDoctorId;
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
        return "DoctorReferral{" + "id=" + id + ", reason='" + reason + '\'' + ", priority=" + priority + ", date=" + date + ", referringDoctorId=" + referringDoctorId + ", patientId=" + patientId + ", referredDoctorId=" + referredDoctorId + ", created=" + created + ", createdBy='" + createdBy + '\'' + ", modified=" + modified + ", modifiedBy='" + modifiedBy + '\'' + '}';
    }
}
