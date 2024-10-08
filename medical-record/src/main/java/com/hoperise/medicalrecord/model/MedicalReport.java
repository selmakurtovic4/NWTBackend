package com.hoperise.medicalrecord.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "medical-report")
public class MedicalReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "diagnosis")
    @NotNull(message ="Diagnosis can't be null!")
    private String diagnosis;
    @Column(name = "treatment_plan")
    @NotNull(message ="Treatment plan can't be null!")
    private String treatmentPlan;
    @Column(name = "tests_results")
    @NotNull(message ="Tests results can't be null!")
    private String testsResults;
    @Column(name = "additional_notes")
    private String additionalNotes;
    @Column(name = "date")
    @NotNull(message = "Date can't be null!")
    private LocalDate date;
    @Column(name = "patient_id")
    @NotNull(message = "Patient ID can't be null!")
    private Long patientId;
    @Column(name = "doctor_id")
    @NotNull(message = "Doctor ID can't be null!")
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


    public MedicalReport() {
    }

    public MedicalReport(String diagnosis, String treatmentPlan, String testsResults, String additionalNotes, LocalDate date, Long patientId, Long doctorId, LocalDateTime created, String createdBy, LocalDateTime modified, String modifiedBy) {
        this.diagnosis = diagnosis;
        this.treatmentPlan = treatmentPlan;
        this.testsResults = testsResults;
        this.additionalNotes = additionalNotes;
        this.date = date;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.created = created;
        this.createdBy = createdBy;
        this.modified = modified;
        this.modifiedBy = modifiedBy;
    }

    public MedicalReport(String diagnosis, String treatmentPlan, String testsResults, String additionalNotes, LocalDate date, Long patientId, Long doctorId, LocalDateTime created, String createdBy) {
        this.diagnosis = diagnosis;
        this.treatmentPlan = treatmentPlan;
        this.testsResults = testsResults;
        this.additionalNotes = additionalNotes;
        this.date = date;
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

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatmentPlan() {
        return treatmentPlan;
    }

    public void setTreatmentPlan(String treatmentPlan) {
        this.treatmentPlan = treatmentPlan;
    }

    public String getTestsResults() {
        return testsResults;
    }

    public void setTestsResults(String testsResults) {
        this.testsResults = testsResults;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
        return "MedicalReport{" +
                "id=" + id +
                ", diagnosis='" + diagnosis + '\'' +
                ", treatment_plan='" + treatmentPlan + '\'' +
                ", tests_results='" + testsResults + '\'' +
                ", additional_notes='" + additionalNotes + '\'' +
                ", report_date=" + date +
                ", patientId=" + patientId +
                ", doctorId=" + doctorId +
                ", created=" + created +
                ", createdBy='" + createdBy + '\'' +
                ", modified=" + modified +
                ", modifiedBy='" + modifiedBy + '\'' +
                '}';
    }
}
