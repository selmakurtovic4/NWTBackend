package com.hoperise.medicalrecord;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.hoperise.medicalrecord.conf.JPAConfig;
import com.hoperise.medicalrecord.model.MedicalInformation;
import com.hoperise.medicalrecord.repository.MedicalInformationRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

@SpringBootTest(classes = {MedicalRecordApplication.class, JPAConfig.class})
@Transactional
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class MedicalInformationControllerTest {
    @Autowired
    private MedicalInformationRepository medicalInformationRepository;

    @Autowired
    private MockMvc mockMvc;

    private static ObjectMapper objectMapper;

    @BeforeAll
    public static void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(new ParameterNamesModule());
    }

    @BeforeEach
    public void initialData() {
        var creationDate = LocalDateTime.now();
        var createdBy = "Amina";

        var medicalInformation = new ArrayList<MedicalInformation>();
        medicalInformation.add(new MedicalInformation(null, null, null, null, 180, 80, 1L, creationDate, createdBy));
        medicalInformation.add(new MedicalInformation(null, null, null, null, 180, 80, 2L, creationDate, createdBy));
        medicalInformation.add(new MedicalInformation(null, null, null, null, 180, 80, 3L, creationDate, createdBy));
        medicalInformation.add(new MedicalInformation(null, null, null, null, 180, 80, 4L, creationDate, createdBy));
        medicalInformation.add(new MedicalInformation(null, null, null, null, 180, 80, 5L, creationDate, createdBy));
        medicalInformation.add(new MedicalInformation(null, null, null, null, 180, 80, 6L, creationDate, createdBy));
        medicalInformation.add(new MedicalInformation(null, null, null, null, 180, 80, 7L, creationDate, createdBy));
        medicalInformationRepository.saveAll(medicalInformation);
    }

    @AfterEach
    public void deleteData() {
        medicalInformationRepository.deleteAll();
    }


}
