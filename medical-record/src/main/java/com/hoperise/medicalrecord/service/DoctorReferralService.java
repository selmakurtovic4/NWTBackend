package com.hoperise.medicalrecord.service;

import com.hoperise.medicalrecord.model.DoctorReferral;
import com.hoperise.medicalrecord.repository.DoctorReferralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorReferralService {
    @Autowired
    private DoctorReferralRepository doctorReferralRepository;

    public List<DoctorReferral> getAllDoctorReferrals() {
        return doctorReferralRepository.findAll();
    }

    public List<DoctorReferral> getAllDoctorReferralsForReferringDoctor(Long id) {
        return doctorReferralRepository.findByReferringDoctorId(id);
    }

    public List<DoctorReferral> getAllDoctorReferralsForPatient(Long id) {
        return doctorReferralRepository.findByPatientId(id);
    }

    public List<DoctorReferral> getAllDoctorReferralsForReferredDoctor(Long id) {
        return doctorReferralRepository.findByReferredDoctorId(id);
    }

    public Optional<DoctorReferral> getDoctorReferral(Long id) {
        return doctorReferralRepository.findById(id);
    }

    public DoctorReferral createDoctorReferral(DoctorReferral doctorReferral) {
        if (doctorReferralRepository.existsByDateAndPatientId(doctorReferral.getDate(), doctorReferral.getPatientId())) {
            return new DoctorReferral();
        }

        DoctorReferral newDoctorReferral = new DoctorReferral();
        newDoctorReferral.setReason(doctorReferral.getReason());
        newDoctorReferral.setPriority(doctorReferral.getPriority());
        newDoctorReferral.setDate(doctorReferral.getDate());
        newDoctorReferral.setReferringDoctorId(doctorReferral.getReferringDoctorId());
        newDoctorReferral.setPatientId(doctorReferral.getPatientId());
        newDoctorReferral.setReferredDoctorId(doctorReferral.getReferredDoctorId());
        newDoctorReferral.setCreated(LocalDateTime.now());
        newDoctorReferral.setCreatedBy("Amina");

        return doctorReferralRepository.save(newDoctorReferral);
    }

    public boolean deleteDoctorReferral(Long id) {
        if (doctorReferralRepository.existsById(id)) {
            doctorReferralRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
