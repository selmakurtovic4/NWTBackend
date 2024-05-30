package com.hoperise.staff.models;

import jakarta.persistence.*;

@Entity
public class StaffMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Enumerated(EnumType.STRING)
    private StaffRole role; // Adding the role field

    // Constructors, getters, and setters
    // You may need to add constructors, getters, and setters for the new role field

    public StaffRole getRole() {
        return role;
    }

    public void setRole(StaffRole role) {
        this.role = role;
    }
}
