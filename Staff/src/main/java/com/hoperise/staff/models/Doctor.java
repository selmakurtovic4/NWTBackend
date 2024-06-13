package com.hoperise.staff.models;

import jakarta.persistence.*;

@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    private String specialization;
    private String biography;

    public Doctor(User user, Department department, String specialization, String biography) {
        this.user = user;
        this.department = department;
        this.specialization = specialization;
        this.biography = biography;
    }
}