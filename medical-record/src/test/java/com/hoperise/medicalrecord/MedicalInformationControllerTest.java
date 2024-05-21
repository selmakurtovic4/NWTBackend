package com.hoperise.medicalrecord;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.hoperise.medicalrecord.conf.JPAConfig;
import com.hoperise.medicalrecord.model.MedicalInformation;
import com.hoperise.medicalrecord.model.MedicalInformation;
import com.hoperise.medicalrecord.repository.MedicalInformationRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Test
    public void getAllMedicalInformationShouldReturnAllMedicalInformation() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/medical-information/all")).andExpect(status().is2xxSuccessful()).andReturn();
        String content = result.getResponse().getContentAsString();
        var MedicalInformation = objectMapper.readValue(content, List.class);

        Assertions.assertFalse(MedicalInformation.isEmpty());
    }

    @Test
    public void getMedicalInformationByIdShouldReturnMedicalInformation() throws Exception {
        var allInformation = medicalInformationRepository.findAll();

        var existingInformation = allInformation.stream()
                .findFirst().isPresent() ?
                allInformation.stream().findFirst().get() : new MedicalInformation();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/medical-information/all/{id}", existingInformation.getId()))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        var retrievedInformation = objectMapper.readValue(content, MedicalInformation.class);

        Assertions.assertEquals(existingInformation.getId(), retrievedInformation.getId());
        Assertions.assertEquals(existingInformation.getPatientId(), retrievedInformation.getPatientId());
    }

    @Test
    public void createAndDeleteMedicalInformationShouldCreateAndDeleteTheCreated() throws Exception {
        var creationDate = LocalDateTime.now();
        var createdBy = "Amina";

        MedicalInformation newMedicalInformation = new MedicalInformation(null, null, null, null, 180, 80, 21L, creationDate, createdBy);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/medical-information/create")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newMedicalInformation)))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        var createdInformation = objectMapper.readValue(content, MedicalInformation.class);

        Assertions.assertNotNull(createdInformation.getId());

        result = mockMvc.perform(MockMvcRequestBuilders.
                        delete("/medical-information/delete/{id}", createdInformation.getId()))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        content = result.getResponse().getContentAsString();

        //msg
        Assertions.assertTrue(content.contains("Medical information deleted successfully!"));
    }

    @Test
    public void createMedicalInformationShouldNotCreateExisting() throws Exception {
        var creationDate = LocalDateTime.now();
        var createdBy = "Amina";

        MedicalInformation newMedicalInformation = new MedicalInformation(null, null, null, null, 180, 80, 1L, creationDate, createdBy);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/medical-information/create")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newMedicalInformation)))
                .andExpect(status().is4xxClientError())
                .andReturn();
        String content = result.getResponse().getContentAsString();

        //msg
        Assertions.assertTrue(content.contains("Medical information for this patient already exists!"));
    }

    @Test
    public void createMedicalInformationShouldNotCreateWithoutWeight() throws Exception {
        var creationDate = LocalDateTime.now();
        var createdBy = "Amina";

        MedicalInformation newMedicalInformation = new MedicalInformation(null, null, null, null, 180, 510, 21L, creationDate, createdBy);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/medical-information/create")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newMedicalInformation)))
                .andExpect(status().is4xxClientError())
                .andReturn();
        String content = result.getResponse().getContentAsString();

        Assertions.assertTrue(content.contains("Weight must be less than or equal to 500!"));
    }

    @Test
    public void deleteMedicalInformationShouldReturnErrorForNonExisting() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.
                        delete("/medical-information/delete/{id}", 20L))
                .andExpect(status().is4xxClientError())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        Assertions.assertTrue(content.contains("Medical information with that ID doesn't exist!"));
    }
}
