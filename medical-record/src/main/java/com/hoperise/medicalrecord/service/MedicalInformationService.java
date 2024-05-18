package com.hoperise.medicalrecord.service;

import com.hoperise.medicalrecord.model.MedicalInformation;
import com.hoperise.medicalrecord.repository.MedicalInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MedicalInformationService {
    @Autowired
    private MedicalInformationRepository medicalInformationRepository;

    public List<MedicalInformation> getAllMedicalInformation() {
        return medicalInformationRepository.findAll();
    }

    public MedicalInformation createMedicalInformation(MedicalInformation medicalInformation) {
        if (medicalInformationRepository.existsByPatientId(medicalInformation.getPatientId())) {
            throw new RuntimeException("Medical information for this patient already exists!");
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

    public String deleteMedicalInformation(Long id) {
        if (medicalInformationRepository.existsById(id)) {
            medicalInformationRepository.deleteById(id);
            return "Medical information deleted successfully";
        }
        return "Medical information with that ID doesn't exist";
    }
}
