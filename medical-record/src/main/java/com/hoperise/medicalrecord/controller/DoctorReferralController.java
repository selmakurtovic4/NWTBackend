package com.hoperise.medicalrecord.controller;

import com.hoperise.medicalrecord.model.DoctorReferral;
import com.hoperise.medicalrecord.repository.DoctorReferralRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(path = "/all/{referringDoctorId}")
    public List<DoctorReferral> getAllDoctorReferralsForReferringDoctor(@PathVariable Long referringDoctorId) {// Implement logic to retrieve all doctor referrals for the specified patient ID
        return doctorReferralRepository.findByReferringDoctorId(referringDoctorId);
    }

    @GetMapping(path = "/all/{patientId}")
    public List<DoctorReferral> getAllDoctorReferralsForPatient(@PathVariable Long patientId) {// Implement logic to retrieve all doctor referrals for the specified patient ID
        return doctorReferralRepository.findByPatientId(patientId);
    }

    @GetMapping(path = "/all/{referredDoctorId}")
    public List<DoctorReferral> getAllDoctorReferralsForReferredDoctor(@PathVariable Long referredDoctorId) {// Implement logic to retrieve all doctor referrals for the specified patient ID
        return doctorReferralRepository.findByReferredDoctorId(referredDoctorId);
    }

    @PostMapping("/create")
    public DoctorReferral create(@RequestBody DoctorReferral doctorReferral) {
        return doctorReferralRepository.save(doctorReferral);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        doctorReferralRepository.deleteById(id);
    }
}
