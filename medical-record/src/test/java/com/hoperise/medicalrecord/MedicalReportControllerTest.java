package com.hoperise.medicalrecord;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.hoperise.medicalrecord.conf.JPAConfig;
import com.hoperise.medicalrecord.model.MedicalReport;
import com.hoperise.medicalrecord.model.Priority;
import com.hoperise.medicalrecord.repository.MedicalReportRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {MedicalRecordApplication.class, JPAConfig.class})
@Transactional
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class MedicalReportControllerTest {
    @Autowired
    private MedicalReportRepository medicalReportRepository;

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
        var reportDate = LocalDate.now();

        var medicalReports = new ArrayList<MedicalReport>();
        medicalReports.add(new MedicalReport("Diagnosis", "Treatment plan", "Tests results", null, reportDate, 1L, 1L, creationDate, createdBy));
        medicalReports.add(new MedicalReport("Diagnosis1", "Treatment plan", "Tests results", null, reportDate, 2L, 1L, creationDate, createdBy));
        medicalReports.add(new MedicalReport("Diagnosis2", "Treatment plan", "Tests results", null, reportDate, 3L, 1L, creationDate, createdBy));
        medicalReports.add(new MedicalReport("Diagnosis3", "Treatment plan", "Tests results", null, reportDate, 4L, 1L, creationDate, createdBy));
        medicalReports.add(new MedicalReport("Diagnosis4", "Treatment plan", "Tests results", null, reportDate, 5L, 1L, creationDate, createdBy));
        medicalReports.add(new MedicalReport("Diagnosis5", "Treatment plan", "Tests results", null, reportDate, 1L, 2L, creationDate, createdBy));
        medicalReports.add(new MedicalReport("Diagnosis6", "Treatment plan", "Tests results", null, reportDate, 1L, 3L, creationDate, createdBy));
        medicalReports.add(new MedicalReport("Diagnosis7", "Treatment plan", "Tests results", null, reportDate, 1L, 4L, creationDate, createdBy));
        medicalReports.add(new MedicalReport("Diagnosis8", "Treatment plan", "Tests results", null, reportDate, 2L, 2L, creationDate, createdBy));
        medicalReports.add(new MedicalReport("Diagnosis9", "Treatment plan", "Tests results", null, reportDate, 2L, 3L, creationDate, createdBy));
        medicalReports.add(new MedicalReport("Diagnosis10", "Treatment plan", "Tests results", null, reportDate, 2L, 4L, creationDate, createdBy));
        medicalReportRepository.saveAll(medicalReports);
    }

    public void deleteData() {
        medicalReportRepository.deleteAll();
    }

    @Test
    public void getAllMedicalReportsShouldReturnAllMedicalReports() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/medical-report/all")).andExpect(status().is2xxSuccessful()).andReturn();
        String content = result.getResponse().getContentAsString();
        var MedicalReports = objectMapper.readValue(content, List.class);

        Assertions.assertFalse(MedicalReports.isEmpty());
    }

    @Test
    public void getMedicalReportByIdShouldReturnMedicalReport() throws Exception {
        var allReports = medicalReportRepository.findAll();

        var existingReport = allReports.stream()
                .findFirst().isPresent() ?
                allReports.stream().findFirst().get() : new MedicalReport();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/medical-report/all/{id}", existingReport.getId()))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        var retrievedReport = objectMapper.readValue(content, MedicalReport.class);

        Assertions.assertEquals(existingReport.getId(), retrievedReport.getId());
        Assertions.assertEquals(existingReport.getPatientId(), retrievedReport.getPatientId());
        Assertions.assertEquals(existingReport.getDate(), retrievedReport.getDate());
    }

    @Test
    public void createAndDeleteMedicalReportShouldCreateAndDeleteTheCreated() throws Exception {
        var creationDate = LocalDateTime.now();
        var createdBy = "Amina";
        var reportDate = LocalDate.now();

       MedicalReport newMedicalReport = new MedicalReport("Diagnosis", "Treatment plan", "Tests results", null, reportDate.plusDays(1), 1L, 1L, creationDate, createdBy);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/medical-report/create")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newMedicalReport)))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        var createdReport = objectMapper.readValue(content, MedicalReport.class);

        Assertions.assertNotNull(createdReport.getId());

        result = mockMvc.perform(MockMvcRequestBuilders.
                        delete("/medical-report/delete/{id}", createdReport.getId()))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        content = result.getResponse().getContentAsString();

        //msg
        Assertions.assertTrue(content.contains("Medical report deleted successfully!"));
    }

    @Test
    public void createMedicalReportShouldNotCreateExisting() throws Exception {
        var creationDate = LocalDateTime.now();
        var createdBy = "Amina";
        var reportDate = LocalDate.now();

        MedicalReport newMedicalReport = new MedicalReport("Diagnosis", "Treatment plan", "Tests results", null, reportDate, 1L, 1L, creationDate, createdBy);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/medical-report/create")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newMedicalReport)))
                .andExpect(status().is4xxClientError())
                .andReturn();
        String content = result.getResponse().getContentAsString();

        //msg
        Assertions.assertTrue(content.contains("A medical report for this patient on the given date already exists."));
    }

    @Test
    public void createMedicalReportShouldNotCreateWithoutDiagnosis() throws Exception {
        var creationDate = LocalDateTime.now();
        var createdBy = "Amina";
        var reportDate = LocalDate.now();

        MedicalReport newMedicalReport = new MedicalReport(null, "Treatment plan", "Tests results", null, reportDate, 1L, 1L, creationDate, createdBy);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/medical-report/create")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newMedicalReport)))
                .andExpect(status().is4xxClientError())
                .andReturn();
        String content = result.getResponse().getContentAsString();

        Assertions.assertTrue(content.contains("Diagnosis can't be null!"));
    }

    @Test
    public void deleteMedicalReportShouldReturnErrorForNonExisting() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.
                        delete("/medical-report/delete/{id}", 20L))
                .andExpect(status().is4xxClientError())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        Assertions.assertTrue(content.contains("Medical report with that ID doesn't exist!"));
    }
}
