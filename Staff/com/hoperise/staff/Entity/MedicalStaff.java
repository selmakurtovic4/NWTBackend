package com.hoperise.staff.Entity;

import jakarta.persistence.Entity;

@jakarta.persistence.Entity
@jakarta.persistence.Table(name = "medical_staff")
@Entity
public class MedicalStaff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String specialization;

    @OneToOne
    private User user;

    // Constructors, getters, and setters

    public MedicalStaff() {
        // Default constructor
    }

    public MedicalStaff(String firstName, String lastName, String specialization, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
        this.user = user;
    }

    // Getters and setters for id, firstName, lastName, specialization, and user

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}