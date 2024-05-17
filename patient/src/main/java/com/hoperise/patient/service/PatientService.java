package com.hoperise.patient.service;

import com.hoperise.patient.model.Patient;
import com.hoperise.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PatientService {

  @Autowired
  private PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getPatientsByLastName(String lastname) {
        return patientRepository.findByLastName(lastname);
    }

    public Patient getPatientById(Long userId) {
        return patientRepository.findPatientByUserId(userId);
    }
}
/*@SpringBootApplication
public class PatientApplication {

    public static void main(String[] args) {
		SpringApplication.run(PatientApplication.class, args);
	}

	private final PatientRepository patientRepository;

	@Autowired
	public PatientApplication(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	public List<Patient> getPatientsByLastName(String lastname) {
		return patientRepository.findByLastName(lastname);
	}

	public Patient getPatientById(Long userId) {
		return patientRepository.findPatientByUserId(userId);
	}

}
*/