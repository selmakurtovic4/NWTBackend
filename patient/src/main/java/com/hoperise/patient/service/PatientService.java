package com.hoperise.patient.service;

import com.hoperise.patient.model.Patient;
import com.hoperise.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PatientService {

  @Autowired
  private PatientRepository patientRepository;

   /* public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }*/

   /* public List<Patient> getPatientsByLastName(String last_name) {
        return patientRepository.findByLastName(last_name);
    }*/

    public Patient getPatientById(Long userId) {
        return patientRepository.findPatientByUserId(userId);
    }

    public Patient getPatient(Long id) {
        return patientRepository.findById(id).get();
    }

    public boolean checkIfExists(Long id) { return patientRepository.findById(id).isPresent(); }

    public Patient createPatient(Patient patient) {
        if (patientRepository.existsById(patient.getId())) {
            throw new RuntimeException("A patient with this ID already exists.");
        }

        Patient newPatient = new Patient();
        newPatient.setCity(patient.getCity());
       // newPatient.setId(patient.getId());
        newPatient.setJmbg(patient.getJmbg());
        newPatient.setAdress(patient.getAdress());
        newPatient.setGender(patient.getGender());
        newPatient.setCity(patient.getCity());
        newPatient.setFirst_name(patient.getFirst_name());
        newPatient.setLastName(patient.getLastName());
        newPatient.setDate_of_birth(patient.getDate_of_birth());
        newPatient.setPhone_number(patient.getPhone_number());

        return patientRepository.save(newPatient);
    }

    public String deletePatient(Long id){
        if (patientRepository.existsById(id)) {
           // throw new RuntimeException("A patient with this ID already exists.");
            patientRepository.deleteById(id);
            return ("Patient was deleted successfully.");
        }
        return ("A patient with this ID doesn't exist.");

    }

    public Long getPatientsByLastName(String lastName) {
        List<Patient> patients = patientRepository.findByLastName(lastName);
        Patient patient = patients.get(0);
        return patient.getId();
    }
}