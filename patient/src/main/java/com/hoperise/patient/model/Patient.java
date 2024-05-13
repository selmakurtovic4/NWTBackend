package com.hoperise.patient.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "jmbg")
    @NotNull(message = "JMBG must be specified!")
    private Long jmbg;
    @Column(name = "first_name")
    @NotNull(message = "First name must be specified!")
    private String first_name;

    @Column(name = "last_name")
    @NotNull(message = "Last name must be specified!")
    private String last_name;
    @Column(name = "date_of_birth")
    @NotNull(message = "Date of birth must be specified!")
    private LocalDate date_of_birth;

    @Column(name = "city")
    @NotNull(message = "City name must be specified!")
    private String city;

    @Column(name = "adress")
    @NotNull(message = "Adress must be specified!")
    private String adress;

    @Column(name = "phone_number")
    private Long phone_number;
    @Column(name="gender")
    private String gender;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
