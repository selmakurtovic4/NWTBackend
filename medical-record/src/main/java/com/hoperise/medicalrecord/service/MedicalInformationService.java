package com.hoperise.medicalrecord.service;

import com.hoperise.medicalrecord.model.MedicalInformation;
import com.hoperise.medicalrecord.repository.MedicalInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MedicalInformationService {
    @Autowired
    private MedicalInformationRepository medicalInformationRepository;

    public List<MedicalInformation> getAllMedicalInformation() {
        return medicalInformationRepository.findAll();
    }

    public Optional<MedicalInformation> getMedicalInformation(Long id) {
        return medicalInformationRepository.findById(id);
    }

    public MedicalInformation createMedicalInformation(MedicalInformation medicalInformation) {
        if (medicalInformationRepository.existsByPatientId(medicalInformation.getPatientId())) {
            return new MedicalInformation();
        }

        MedicalInformation newMedicalInformation = new MedicalInformation();
        newMedicalInformation.setAllergies(medicalInformation.getAllergies());
        newMedicalInformation.setMedications(medicalInformation.getMedications());
        newMedicalInformation.setBlood_type(medicalInformation.getBlood_type());
        newMedicalInformation.setFamily_history(medicalInformation.getFamily_history());
        newMedicalInformation.setHeight(medicalInformation.getHeight());
        newMedicalInformation.setWeight(medicalInformation.getWeight());
        newMedicalInformation.setPatientId(medicalInformation.getPatientId());
        newMedicalInformation.setCreated(LocalDateTime.now());
        newMedicalInformation.setCreatedBy("Amina");

        return medicalInformationRepository.save(newMedicalInformation);
    }

    public boolean deleteMedicalInformation(Long id) {
        if (medicalInformationRepository.existsById(id)) {
            medicalInformationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
