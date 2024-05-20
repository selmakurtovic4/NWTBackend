package com.hoperise.medicalrecord;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.hoperise.medicalrecord.conf.JPAConfig;
import com.hoperise.medicalrecord.model.DoctorReferral;
import com.hoperise.medicalrecord.model.Priority;
import com.hoperise.medicalrecord.repository.DoctorReferralRepository;
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
import java.util.function.Predicate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {MedicalRecordApplication.class, JPAConfig.class})
@Transactional
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class DoctorReferralControllerTest {
    @Autowired
    private DoctorReferralRepository doctorReferralRepository;

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

        var doctorReferrals = new ArrayList<DoctorReferral>();
        doctorReferrals.add(new DoctorReferral("Check up", Priority.NON_URGENT, LocalDateTime.of(2024, 5, 21, 10, 10), 1L, 1L, 2L, creationDate, createdBy));
        doctorReferrals.add(new DoctorReferral("Check up", Priority.NON_URGENT, LocalDateTime.of(2024, 6, 21, 9, 10), 2L, 2L, 1L, creationDate, createdBy));
        doctorReferrals.add(new DoctorReferral("CT scan", Priority.URGENT, LocalDateTime.of(2024, 5, 21, 8, 10), 3L, 3L, 2L, creationDate, createdBy));
        doctorReferrals.add(new DoctorReferral("Surgery", Priority.VERY_URGENT, LocalDateTime.of(2024, 7, 10, 10, 10), 1L, 4L, 3L, creationDate, createdBy));
        doctorReferrals.add(new DoctorReferral("Biopsy", Priority.VERY_URGENT, LocalDateTime.of(2024, 8, 10, 10, 10), 2L, 1L, 1L, creationDate, createdBy));
        doctorReferrals.add(new DoctorReferral("Dentist", Priority.URGENT, LocalDateTime.of(2024, 9, 21, 10, 10), 3L, 2L, 2L, creationDate, createdBy));
        doctorReferrals.add(new DoctorReferral("Eye check up", Priority.NON_URGENT, LocalDateTime.of(2024, 5, 21, 9, 9), 1L, 3L, 3L, creationDate, createdBy));
        doctorReferrals.add(new DoctorReferral("Heart scan", Priority.URGENT, LocalDateTime.of(2024, 6, 10, 8, 10), 2L, 4L, 1L, creationDate, createdBy));
        doctorReferrals.add(new DoctorReferral("Lung scan", Priority.URGENT, LocalDateTime.of(2024, 7, 10, 10, 10), 3L, 5L, 2L, creationDate, createdBy));
        doctorReferrals.add(new DoctorReferral("Sewing up patient's arm", Priority.VERY_URGENT, LocalDateTime.of(2024, 8, 21, 10, 0), 1L, 1L, 3L, creationDate, createdBy));
        doctorReferralRepository.saveAll(doctorReferrals);
    }

    @AfterEach
    public void deleteData() {
        doctorReferralRepository.deleteAll();
    }

    @Test
    public void getAllDoctorReferralsShouldReturnAllDoctorReferrals() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/doctor-referral/all")).andExpect(status().is2xxSuccessful()).andReturn();
        String content = result.getResponse().getContentAsString();
        var doctorReferrals = objectMapper.readValue(content, List.class);

        Assertions.assertFalse(doctorReferrals.isEmpty());
    }

    @Test
    public void getAllDoctorReferralsForReferringDoctorShouldReturnAllDoctorReferrals() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/doctor-referral/all/referring-doctor/{id}", 1L)).andExpect(status().is2xxSuccessful()).andReturn();
        String content = result.getResponse().getContentAsString();
        List<DoctorReferral> doctorReferrals = objectMapper.readValue(content, new TypeReference<List<DoctorReferral>>() {
        });

        Predicate<DoctorReferral> isIdEqual = dr -> dr.getReferringDoctorId().equals(1L);

        Assertions.assertFalse(doctorReferrals.isEmpty());
        Assertions.assertTrue(doctorReferrals.stream().allMatch(isIdEqual));
    }

    @Test
    public void getAllDoctorReferralsForPatientShouldReturnAllDoctorReferrals() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/doctor-referral/all/patient/{id}", 1L)).andExpect(status().is2xxSuccessful()).andReturn();
        String content = result.getResponse().getContentAsString();
        List<DoctorReferral> doctorReferrals = objectMapper.readValue(content, new TypeReference<List<DoctorReferral>>() {
        });

        Predicate<DoctorReferral> isIdEqual = dr -> dr.getPatientId().equals(1L);

        Assertions.assertFalse(doctorReferrals.isEmpty());
        Assertions.assertTrue(doctorReferrals.stream().allMatch(isIdEqual));
    }

    @Test
    public void getAllDoctorReferralsForReferredDoctorShouldReturnAllDoctorReferrals() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/doctor-referral/all/referred-doctor/{id}", 1L)).andExpect(status().is2xxSuccessful()).andReturn();
        String content = result.getResponse().getContentAsString();
        List<DoctorReferral> doctorReferrals = objectMapper.readValue(content, new TypeReference<List<DoctorReferral>>() {
        });

        Predicate<DoctorReferral> isIdEqual = dr -> dr.getReferredDoctorId().equals(1L);

        Assertions.assertFalse(doctorReferrals.isEmpty());
        Assertions.assertTrue(doctorReferrals.stream().allMatch(isIdEqual));
    }

    @Test
    public void getDoctorReferralByIdShouldReturnDoctorReferral() throws Exception {
        var allReferrals = doctorReferralRepository.findAll();

        var existingReferral = allReferrals.stream()
                .findFirst().isPresent() ?
                allReferrals.stream().findFirst().get() : new DoctorReferral();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/doctor-referral/all/{id}", existingReferral.getId()))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        var retrievedReferral = objectMapper.readValue(content, DoctorReferral.class);

        Assertions.assertEquals(existingReferral.getId(), retrievedReferral.getId());
        Assertions.assertEquals(existingReferral.getReferredDoctorId(), retrievedReferral.getReferredDoctorId());
        Assertions.assertEquals(existingReferral.getPatientId(), retrievedReferral.getPatientId());
        Assertions.assertEquals(existingReferral.getReferringDoctorId(), retrievedReferral.getReferringDoctorId());
    }

    @Test
    public void createAndDeleteDoctorReferralShouldCreateAndDeleteTheCreated() throws Exception {
        var creationDate = LocalDateTime.now();
        var createdBy = "Amina";

        DoctorReferral newDoctorReferral = new DoctorReferral("Check up", Priority.NON_URGENT, LocalDateTime.of(2024, 10, 21, 10, 10), 1L, 3L, 2L, creationDate, createdBy);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/doctor-referral/create")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newDoctorReferral)))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        var createdDoctorReferral = objectMapper.readValue(content, DoctorReferral.class);

        Assertions.assertNotNull(createdDoctorReferral.getId());

        result = mockMvc.perform(MockMvcRequestBuilders.
                        delete("/doctor-referral/delete/{id}", createdDoctorReferral.getId()))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        content = result.getResponse().getContentAsString();

        Assertions.assertTrue(content.contains("Doctor referral deleted successfully!"));
    }


    @Test
    public void createDoctorReferralShouldNotCreateExisting() throws Exception {
        var creationDate = LocalDateTime.now();
        var createdBy = "Amina";

        DoctorReferral newDoctorReferral = new DoctorReferral("Check up", Priority.NON_URGENT, LocalDateTime.of(2024, 5, 21, 10, 10), 1L, 1L, 2L, creationDate, createdBy);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/doctor-referral/create")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newDoctorReferral)))
                .andExpect(status().is4xxClientError())
                .andReturn();
        String content = result.getResponse().getContentAsString();

        Assertions.assertTrue(content.contains("A doctor referral for this patient on the given date and time already exists."));
    }

    @Test
    public void createDoctorReferralShouldNotCreateWithoutReason() throws Exception {
        var creationDate = LocalDateTime.now();
        var createdBy = "Amina";

        DoctorReferral newDoctorReferral = new DoctorReferral(null, Priority.NON_URGENT, LocalDateTime.of(2024, 5, 21, 10, 10), 1L, 1L, 2L, creationDate, createdBy);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/doctor-referral/create")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newDoctorReferral)))
                .andExpect(status().is4xxClientError())
                .andReturn();
        String content = result.getResponse().getContentAsString();

        Assertions.assertTrue(content.contains("Reason can't be null!"));
    }

    @Test
    public void deleteDoctorReferralShouldReturnErrorForNonExisting() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.
                        delete("/doctor-referral/delete/{id}", 20L))
                .andExpect(status().is4xxClientError())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        Assertions.assertTrue(content.contains("Doctor referral with that ID doesn't exist!"));
    }
}
