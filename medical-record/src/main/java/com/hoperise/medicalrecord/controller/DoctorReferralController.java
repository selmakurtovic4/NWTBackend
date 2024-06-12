package com.hoperise.medicalrecord.controller;

import com.hoperise.medicalrecord.model.DoctorReferral;
import com.hoperise.medicalrecord.service.DoctorReferralService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RefreshScope
@RequestMapping("/doctor-referral")
public class DoctorReferralController {
    @Autowired
    private final DoctorReferralService doctorReferralService;

    public DoctorReferralController(DoctorReferralService doctorReferralService) {
        this.doctorReferralService = doctorReferralService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/all")
    public @ResponseBody ResponseEntity<List<DoctorReferral>> getAllDoctorReferrals() {
        var doctorReferrals = doctorReferralService.getAllDoctorReferrals();
        return new ResponseEntity<>(doctorReferrals, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping(path = "/all/referring-doctor/{id}")
    public @ResponseBody ResponseEntity<List<DoctorReferral>> getAllDoctorReferralsForReferringDoctor(@PathVariable Long id) {
        var doctorReferrals = doctorReferralService.getAllDoctorReferralsForReferringDoctor(id);
        return new ResponseEntity<>(doctorReferrals, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PATIENT')")
    @GetMapping(path = "/all/patient/{id}")
    public @ResponseBody ResponseEntity<List<DoctorReferral>> getAllDoctorReferralsForPatient(@PathVariable Long id) {
        var doctorReferrals = doctorReferralService.getAllDoctorReferralsForPatient(id);
        return new ResponseEntity<>(doctorReferrals, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping(path = "/all/referred-doctor/{id}")
    public @ResponseBody ResponseEntity<List<DoctorReferral>> getAllDoctorReferralsForReferredDoctor(@PathVariable Long id) {
        var doctorReferrals = doctorReferralService.getAllDoctorReferralsForReferredDoctor(id);
        return new ResponseEntity<>(doctorReferrals, HttpStatus.OK);
    }

    @GetMapping(path = "/all/{id}")
    public @ResponseBody ResponseEntity<Optional<DoctorReferral>> getDoctorReferral(@PathVariable Long id) {
        return new ResponseEntity<>(doctorReferralService.getDoctorReferral(id), HttpStatus.OK);

    }

    @PreAuthorize("hasRole('DOCTOR')")
    @PostMapping("/create")
    public @ResponseBody ResponseEntity<?> createDoctorReferral(@RequestBody @Valid DoctorReferral doctorReferral) {
        var createdReferral = doctorReferralService.createDoctorReferral(doctorReferral);
        if(createdReferral.getId() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", "A doctor referral for this patient on the given date and time already exists."));
        }
        return new ResponseEntity<>(createdReferral, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @DeleteMapping("/delete/{id}")
    public @ResponseBody ResponseEntity<?> deleteDoctorReferral(@PathVariable Long id) {
        boolean deleted = doctorReferralService.deleteDoctorReferral(id);
        if (deleted) {
            return ResponseEntity.ok(Collections.singletonMap("message", "Doctor referral deleted successfully!"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", "Doctor referral with that ID doesn't exist!"));
    }
}
