package com.hoperise.medicalrecord.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "medical-information")
public class MedicalInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "allergies")
    private String allergies;
    @Column(name = "medications")
    private String medications;
    @Column(name = "blood_type")
    private String blood_type;
    @Column(name = "family_history")
    private String family_history;
    @Column(name = "height")
    @NotNull(message = "Height must be specified!")
    private double height;
    @Column(name = "weight")
    @NotNull(message = "Weight must be specified!")
    private double weight;
    @Column(name = "patient_id")
    @NotNull
    private Long patientId;
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

    public MedicalInformation() {
    }

    public MedicalInformation(String allergies, String medications, String blood_type, String family_history, double height, double weight, Long patientId, LocalDateTime created, String createdBy, LocalDateTime modified, String modifiedBy) {
        this.allergies = allergies;
        this.medications = medications;
        this.blood_type = blood_type;
        this.family_history = family_history;
        this.height = height;
        this.weight = weight;
        this.patientId = patientId;
        this.created = created;
        this.createdBy = createdBy;
        this.modified = modified;
        this.modifiedBy = modifiedBy;
    }

    public MedicalInformation(String allergies, String medications, String blood_type, String family_history, double height, double weight, Long patientId, LocalDateTime created, String createdBy) {
        this.allergies = allergies;
        this.medications = medications;
        this.blood_type = blood_type;
        this.family_history = family_history;
        this.height = height;
        this.weight = weight;
        this.patientId = patientId;
        this.created = created;
        this.createdBy = createdBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
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

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getMedications() {
        return medications;
    }

    public void setMedications(String medications) {
        this.medications = medications;
    }

    public String getBlood_type() {
        return blood_type;
    }

    public void setBlood_type(String blood_type) {
        this.blood_type = blood_type;
    }

    public String getFamily_history() {
        return family_history;
    }

    public void setFamily_history(String family_history) {
        this.family_history = family_history;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "MedicalInformation{" +
                "id=" + id +
                ", allergies='" + allergies + '\'' +
                ", medications='" + medications + '\'' +
                ", blood_type='" + blood_type + '\'' +
                ", family_history='" + family_history + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", patientId=" + patientId +
                ", created=" + created +
                ", createdBy='" + createdBy + '\'' +
                ", modified=" + modified +
                ", modifiedBy='" + modifiedBy + '\'' +
                '}';
    }
}
