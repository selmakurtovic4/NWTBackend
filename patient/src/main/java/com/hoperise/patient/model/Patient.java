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
    private String lastName;
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJmbg() {
        return jmbg;
    }

    public void setJmbg(Long jmbg) {
        this.jmbg = jmbg;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(Long phone_number) {
        this.phone_number = phone_number;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Patient(Long id, Long jmbg, String first_name, String lastName, LocalDate date_of_birth, String city, String adress, Long phone_number, String gender, User user) {
        this.id = id;
        this.jmbg = jmbg;
        this.first_name = first_name;
        this.lastName = lastName;
        this.date_of_birth = date_of_birth;
        this.city = city;
        this.adress = adress;
        this.phone_number = phone_number;
        this.gender = gender;
        this.user = user;
    }

    public Patient(Long jmbg, String first_name, String lastName, LocalDate date_of_birth, String city, String adress, Long phone_number, String gender, User user) {
        this.jmbg = jmbg;
        this.first_name = first_name;
        this.lastName = lastName;
        this.date_of_birth = date_of_birth;
        this.city = city;
        this.adress = adress;
        this.phone_number = phone_number;
        this.gender = gender;
        this.user = user;
    }

    public Patient() {
    }
}
