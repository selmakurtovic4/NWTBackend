package com.hoperise.medicalrecord.controller;

import com.hoperise.medicalrecord.model.DoctorReferral;
import com.hoperise.medicalrecord.repository.DoctorReferralRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor-referral")
public class DoctorReferralController {
    @Autowired
    private final DoctorReferralRepository doctorReferralRepository;

    public DoctorReferralController(DoctorReferralRepository doctorReferralRepository) {
        this.doctorReferralRepository = doctorReferralRepository;
    }

    @GetMapping(path = "/all")
    public List<DoctorReferral> getAllDoctorReferrals() {
        return doctorReferralRepository.findAll();
    }

    @GetMapping(path = "/all/referring-doctor/{id}")
    public List<DoctorReferral> getAllDoctorReferralsForReferringDoctor(@PathVariable Long id) {
        return doctorReferralRepository.findByReferringDoctorId(id);
    }

    @GetMapping(path = "/all/patient/{id}")
    public List<DoctorReferral> getAllDoctorReferralsForPatient(@PathVariable Long id) {
        return doctorReferralRepository.findByPatientId(id);
    }

    @GetMapping(path = "/all/referred-doctor/{id}")
    public List<DoctorReferral> getAllDoctorReferralsForReferredDoctor(@PathVariable Long id) {
        return doctorReferralRepository.findByReferredDoctorId(id);
    }

    @PostMapping("/create")
    public DoctorReferral create(@RequestBody @Valid DoctorReferral doctorReferral) {
        if (doctorReferralRepository.existsByDateAndPatientId(doctorReferral.getDate(), doctorReferral.getPatientId())) {
            throw new RuntimeException("A doctor referral for this patient on the given date and time already exists.");
        }
        return doctorReferralRepository.save(doctorReferral);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (doctorReferralRepository.existsById(id)) {
            doctorReferralRepository.deleteById(id);
            return ResponseEntity.ok("Doctor referral deleted successfully"); // Return 200 OK if deletion is successful
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor referral with that ID doesn't exist"); // Return 404 Not Found if entity with the specified ID does not exist
        }
    }
}
