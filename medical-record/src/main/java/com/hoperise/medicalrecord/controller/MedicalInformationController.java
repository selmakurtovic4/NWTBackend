package com.hoperise.medicalrecord.controller;

import com.hoperise.medicalrecord.model.MedicalInformation;
import com.hoperise.medicalrecord.repository.MedicalInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medical-report")
public class MedicalInformationController {

    @Autowired
    private final MedicalInformationRepository medicalInformationRepository;

    public MedicalInformationController(MedicalInformationRepository medicalInformationRepository) {
        this.medicalInformationRepository = medicalInformationRepository;
    }

    @GetMapping(path = "/all")
    public List<MedicalInformation> getAllMedicalInformation() {
        return medicalInformationRepository.findAll();
    }

    @PostMapping("/create")
    public MedicalInformation create(@RequestBody MedicalInformation medicalReport) {
        return medicalInformationRepository.save(medicalReport);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        medicalInformationRepository.deleteById(id);
    }

}
