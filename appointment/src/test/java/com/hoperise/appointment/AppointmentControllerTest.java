package com.hoperise.appointment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.hoperise.appointment.conf.JPAConfig;
import com.hoperise.appointment.model.appointment.Appointment;
import com.hoperise.appointment.model.appointment.AppointmentStatus;
import com.hoperise.appointment.repository.AppointmentRepository;
import com.hoperise.appointment.repository.ReviewRepository;
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

@SpringBootTest(classes = {AppointmentApplication.class, JPAConfig.class})
@Transactional
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class AppointmentControllerTest {


    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

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
        var appointments = new ArrayList<Appointment>();
        appointments.add(new Appointment(
                LocalDate.of(2024, 4, 18),
                LocalTime.of(9, 0),
                AppointmentStatus.AVAILABLE,
                null,
                1L,
                LocalDateTime.now(),
                "User"
        ));

        appointments.add(new Appointment(
                LocalDate.of(2024, 4, 25),
                LocalTime.of(10, 0),
                AppointmentStatus.BOOKED,
                1L,
                2L,
                LocalDateTime.now(),
                "User"
        ));
        appointments.add(new Appointment(
                LocalDate.of(2024, 5, 25),
                LocalTime.of(10, 30),
                AppointmentStatus.BOOKED,
                2L,
                2L,
                LocalDateTime.now(),
                "User"
        ));

        appointmentRepository.saveAll(appointments);
    }

    @AfterEach
    public void deleteData() {
        appointmentRepository.deleteAll();
    }

    @Test
    public void getAllAppointmentsShouldReturnAllAppointments() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/appointment/all"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        var appointments = objectMapper.readValue(content, List.class);


        Assertions.assertFalse(appointments.isEmpty());
    }

    @Test
    public void getAppointmentByIdShouldReturnAppointment() throws Exception {
        var allAppointments = appointmentRepository.findAll();

        var existingAppointment = allAppointments.stream()
                .filter(a -> a.getStatus() == AppointmentStatus.BOOKED)
                .findFirst()
                .get();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/appointment/{id}", existingAppointment.getId()))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        var retrievedAppointment = objectMapper.readValue(content, Appointment.class);

        Assertions.assertEquals(existingAppointment.getId(), retrievedAppointment.getId());
        Assertions.assertEquals(existingAppointment.getStatus(), retrievedAppointment.getStatus());
        Assertions.assertEquals(existingAppointment.getDoctorId(), retrievedAppointment.getDoctorId());
    }

    @Test
    public void addAppointmentShouldReturnCreatedAppointment() throws Exception {
        Appointment newAppointment = new Appointment(LocalDate.now(), LocalTime.now(), AppointmentStatus.AVAILABLE, null, 3L, LocalDateTime.now(), "User");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/appointment/add")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newAppointment)))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        var createdAppointment = objectMapper.readValue(content, Appointment.class);

        Assertions.assertNotNull(createdAppointment.getId());
    }

    @Test
    public void addAppointmentShouldNotAddAppointmentWithoutStatusSpecified() throws Exception {
        Appointment newAppointment = new Appointment(LocalDate.now(), LocalTime.now(), null, null, 3L, LocalDateTime.now(), "User");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/appointment/add")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newAppointment)))
                .andExpect(status().is4xxClientError())
                .andReturn();
        String content = result.getResponse().getContentAsString();

        Assertions.assertTrue(content.contains("Status must be specified!"));
    }

    @Test
    public void deleteShouldDeleteExistingAppointment() throws Exception {
        var allAppointments = appointmentRepository.findAll();

        var id = allAppointments.stream()
                .filter(a -> a.getStatus() == AppointmentStatus.BOOKED)
                .findFirst()
                .get()
                .getId();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.
                        delete("/appointment/{id}", id))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        Assertions.assertTrue(content.contains("Appointment with id " + id + " is successfully deleted!"));
    }

    @Test
    public void deleteShouldReturnErrorForNonExistingAppointment() throws Exception {
        var id = 100;
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.
                        delete("/appointment/{id}", id))
                .andExpect(status().is4xxClientError())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        Assertions.assertTrue(content.contains("Appointment with id " + id + " does not exist!"));
    }

    @Test
    public void updateShouldUpdateAppointment() throws Exception {
        var allAppointments = appointmentRepository.findAll();
        var appointment = allAppointments.stream().filter(a -> a.getStatus().equals(AppointmentStatus.BOOKED)).findFirst().get();

        appointment.setDate(LocalDate.of(2024, 5, 1));
        appointment.setTime(LocalTime.of(11,0));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .put("/appointment/update/{id}", appointment.getId())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(appointment)))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        var updatedAppointment = objectMapper.convertValue(objectMapper.readTree(content), Appointment.class);

        Assertions.assertEquals(LocalDate.of(2024, 5, 1), updatedAppointment.getDate());
        Assertions.assertEquals(LocalTime.of(11, 0), updatedAppointment.getTime());
    }

    @Test
    public void updateShouldNotUpdateAppointmentWithoutStatusSpecified() throws Exception {
        var allAppointments = appointmentRepository.findAll();
        var id = allAppointments.stream().filter(a -> a.getStatus().equals(AppointmentStatus.BOOKED)).findFirst().get().getId();
        var request = new Appointment(
                LocalDate.of(2024, 5, 1),
                LocalTime.of(10,45), null,
                1L, 3L,
                LocalDateTime.now(), "User");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .put("/appointment/update/{id}", id)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().is4xxClientError())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        Assertions.assertTrue(content.contains("Status must be specified!"));
    }

    @Test
    public void getDoctorAppointmentsShouldReturnAppointmentsForDoctor() throws Exception {
        Long doctorId = 1L;
        var expectedAppointments = appointmentRepository.findByDoctorId(doctorId);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/appointment/getAppsByDid/{doctor_id}", doctorId))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        List<Appointment> actualAppointments = objectMapper.readValue(content, objectMapper.getTypeFactory().constructCollectionType(List.class, Appointment.class));

        Assertions.assertEquals(expectedAppointments.size(), actualAppointments.size());
    }

    @Test
    public void getPatientAppointmentsShouldReturnAppointmentsForPatient() throws Exception {
        Long patientId = 1L;
        var expectedAppointments = appointmentRepository.findByPatientId(patientId);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/appointment/getAppsByPid/{patient_id}", patientId))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        List<Appointment> actualAppointments = objectMapper.readValue(content, objectMapper.getTypeFactory().constructCollectionType(List.class, Appointment.class));

        Assertions.assertEquals(expectedAppointments.size(), actualAppointments.size());
    }

}
