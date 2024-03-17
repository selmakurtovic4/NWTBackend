package com.hoperise.staff.Entity;

import jakarta.persistence.Entity;

@jakarta.persistence.Entity
@jakarta.persistence.Table(name = "department")
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String departmentName;
    private String description;
    private String location;
    private Long headDoctorId;

    // Constructors, getters, and setters

    public Department() {
        // Default constructor
    }

    public Department(String departmentName, String description, String location, Long headDoctorId) {
        this.departmentName = departmentName;
        this.description = description;
        this.location = location;
        this.headDoctorId = headDoctorId;
    }

    // Getters and setters for id, departmentName, description, location, and headDoctorId

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getHeadDoctorId() {
        return headDoctorId;
    }

    public void setHeadDoctorId(Long headDoctorId) {
        this.headDoctorId = headDoctorId;
    }
}