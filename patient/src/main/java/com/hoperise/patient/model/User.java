package com.hoperise.patient.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    @NotNull(message = "Username must be specified!")
    private String username;

    @Column(name = "password")
    @NotNull(message = "Password must be specified!")
    private String password;

    @Column(name = "role")
    @NotNull(message = "Role must be specified!")
    private String role;
}
