package com.hoperise.staff.Entity;

import jakarta.persistence.Entity;

@jakarta.persistence.Entity
@jakarta.persistence.Table(name = "non_medical_staff")
@Entity
public class NonMedicalStaff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private Long userId;
    private Long departmentId;

    // Constructors, getters, and setters

    public NonMedicalStaff() {
        // Default constructor
    }

    public NonMedicalStaff(String firstName, String lastName, Long userId, Long departmentId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
        this.departmentId = departmentId;
    }

    // Getters and setters for id, firstName, lastName, userId, and departmentId

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}