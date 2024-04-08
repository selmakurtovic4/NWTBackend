package com.hoperise.medicalrecord.controller;

import com.hoperise.medicalrecord.model.DoctorReferral;
import com.hoperise.medicalrecord.service.DoctorReferralService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doctor-referral")
public class DoctorReferralController {
    @Autowired
    private final DoctorReferralService doctorReferralService;

    public DoctorReferralController(DoctorReferralService doctorReferralService) {
        this.doctorReferralService = doctorReferralService;
    }

    @GetMapping(path = "/all")
    public @ResponseBody ResponseEntity<List<DoctorReferral>> getAllDoctorReferrals() {
        var doctorReferrals = doctorReferralService.getAllDoctorReferrals();
        return new ResponseEntity<>(doctorReferrals, HttpStatus.OK);
    }

    @GetMapping(path = "/all/referring-doctor/{id}")
    public @ResponseBody ResponseEntity<List<DoctorReferral>> getAllDoctorReferralsForReferringDoctor(@PathVariable Long id) {
        var doctorReferrals = doctorReferralService.getAllDoctorReferralsForReferringDoctor(id);
        return new ResponseEntity<>(doctorReferrals, HttpStatus.OK);
    }

    @GetMapping(path = "/all/patient/{id}")
    public @ResponseBody ResponseEntity<List<DoctorReferral>> getAllDoctorReferralsForPatient(@PathVariable Long id) {
        var doctorReferrals = doctorReferralService.getAllDoctorReferralsForPatient(id);
        return new ResponseEntity<>(doctorReferrals, HttpStatus.OK);
    }

    @GetMapping(path = "/all/referred-doctor/{id}")
    public @ResponseBody ResponseEntity<List<DoctorReferral>> getAllDoctorReferralsForReferredDoctor(@PathVariable Long id) {
        var doctorReferrals = doctorReferralService.getAllDoctorReferralsForReferredDoctor(id);
        return new ResponseEntity<>(doctorReferrals, HttpStatus.OK);
    }

    @GetMapping(path = "/all/{id}")
    public @ResponseBody ResponseEntity<Optional<DoctorReferral>> getDoctorReferral(@PathVariable Long id) {
        return new ResponseEntity<>(doctorReferralService.getDoctorReferral(id), HttpStatus.OK);

    }

    @PostMapping("/create")
    public @ResponseBody ResponseEntity<DoctorReferral> createDoctorReferral(@RequestBody @Valid DoctorReferral doctorReferral) {
        return new ResponseEntity<>(doctorReferralService.createDoctorReferral(doctorReferral), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public @ResponseBody ResponseEntity<String> deleteDoctorReferral(@PathVariable Long id) {
        return new ResponseEntity<>(doctorReferralService.deleteDoctorReferral(id), HttpStatus.OK);
    }
}
