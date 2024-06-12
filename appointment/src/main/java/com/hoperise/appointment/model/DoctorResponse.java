package com.hoperise.appointment.model;

import jakarta.persistence.*;

public class DoctorResponse {
    private UserResponse user;
    private String department;
    private String specialization;
    private String biography;
}
