package com.hoperise.appointment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.hoperise.appointment.model.Review;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {AppointmentApplication.class, JPAConfig.class})
@Transactional
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class ReviewControllerTest {

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
        var creationDate = LocalDateTime.now();
		var createdBy = "Adna";
		var appointments = new ArrayList<Appointment>();
		appointments.add(new Appointment(LocalDate.of(2024, 3, 25), LocalTime.of(9,0), AppointmentStatus.AVAILABLE, null,1L, creationDate,createdBy));
		appointments.add(new Appointment(LocalDate.of(2024, 3, 25),LocalTime.of(9, 30), AppointmentStatus.AVAILABLE, null,2L, creationDate,createdBy));
		appointments.add(new Appointment(LocalDate.of(2024, 3, 26),LocalTime.of(10, 0), AppointmentStatus.AVAILABLE, null,1L, creationDate,createdBy));
		appointments.add(new Appointment(LocalDate.of(2024, 3, 27),LocalTime.of(11, 30), AppointmentStatus.CANCELLED, 1L,1L, creationDate,createdBy));
		appointments.add(new Appointment(LocalDate.of(2024, 4, 1),LocalTime.of(12, 0), AppointmentStatus.BOOKED, 2L,2L, creationDate,createdBy));
		appointments.add(new Appointment(LocalDate.of(2024, 4, 1),LocalTime.of(13, 0), AppointmentStatus.AVAILABLE, null,3L, creationDate,createdBy));
		appointments.add(new Appointment(LocalDate.of(2024, 4, 2),LocalTime.of(10, 0), AppointmentStatus.BOOKED, 3L,3L, creationDate,createdBy));

		appointmentRepository.saveAll(appointments);

		var reviews = new ArrayList<Review>();
		reviews.add(new Review(5, "Excellent service!", appointments.get(6)));
		reviews.add(new Review(4, "Good experience overall.", appointments.get(0)));
		reviews.add(new Review(3, "Could be better.", appointments.get(1)));
		reviews.add(new Review(5, "Highly recommend!", appointments.get(2)));
		reviews.add(new Review(2, "Disappointed with the service.", appointments.get(5)));

		reviewRepository.saveAll(reviews);
    }

    @AfterEach
    public void deleteData() {
        appointmentRepository.deleteAll();
        reviewRepository.deleteAll();
    }

    @Test
    public void getAllShouldReturnAllReviews() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/review/all"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        var reviews = objectMapper.convertValue(objectMapper.readTree(content), Review[].class);

        Assertions.assertEquals(5, reviews.length);
    }

    @Test
    public void getReviewByIdShouldReturnReview() throws Exception {
        var allReviews = reviewRepository.findAll();
        var existingReview = allReviews.stream().filter(a -> a.getRating() == 5).findFirst().get();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/review/{id}", existingReview.getId()))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        var retrievedReview = objectMapper.readValue(content, Review.class);

        Assertions.assertEquals(existingReview.getId(), retrievedReview.getId());
        Assertions.assertEquals(existingReview.getRating(), retrievedReview.getRating());
        Assertions.assertEquals(existingReview.getComment(), retrievedReview.getComment());
    }

    @Test
    public void addReviewShouldReturnCreatedReview() throws Exception {
        var allAppointments = appointmentRepository.findAll();
        var appointment = allAppointments.stream().filter(a -> a.getReview() == null && a.getPatientId() != null).findFirst().get();
        Review newReview = new Review();
        newReview.setRating(5);
        newReview.setComment("Comment");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/review/add/{appointmentId}", appointment.getId())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newReview)))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        var createdReview = objectMapper.readValue(content, Review.class);

        Assertions.assertNotNull(createdReview.getId());
        Assertions.assertEquals(newReview.getRating(), createdReview.getRating());
        Assertions.assertEquals(newReview.getComment(), createdReview.getComment());
    }

    @Test
    public void addReviewShouldNotAddReviewForInvalidRating() throws Exception {
        var allAppointments = appointmentRepository.findAll();
        var appointment = allAppointments.stream().filter(a -> a.getPatientId() != null).findFirst().get();
        Review newReview = new Review();
        newReview.setRating(7);
        newReview.setComment("Comment");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/review/add/{appointmentId}", appointment.getId())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newReview)))
                .andExpect(status().is4xxClientError())
                .andReturn();
        String content = result.getResponse().getContentAsString();

        Assertions.assertTrue(content.contains("Rating must be at most 5!"));
    }

    @Test
    public void deleteShouldDeleteExistingReview() throws Exception {
        var allReviews = reviewRepository.findAll();
        var id = allReviews.stream().filter(r->r.getRating() == 5).findFirst().get().getId();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.
                        delete("/review/{id}", id))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        Assertions.assertTrue(content.contains("Review with id " + id + " is successfully deleted!"));
    }

    @Test
    public void deleteShouldReturnErrorForNonExistingReview() throws Exception {
        var id = 100;
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.
                        delete("/review/{id}", id))
                .andExpect(status().is4xxClientError())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        Assertions.assertTrue(content.contains("Review with id " + id + " does not exist!"));
    }

    @Test
    public void updateShouldUpdateReview() throws Exception {
        var allReviews = reviewRepository.findAll();
        var review = allReviews.stream().filter(r->r.getRating() == 5).findFirst().get();

        review.setRating(4);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .put("/review/update/{id}", review.getId())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(review)))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        var updatedReview = objectMapper.convertValue(objectMapper.readTree(content), Review.class);

        Assertions.assertEquals(4, updatedReview.getRating());
    }

    @Test
    public void getAllDoctorReviewsShouldReturnReviewsForDoctor() throws Exception {
        Long doctorId = 1L;
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/review/getDoctorReviews/{doctorId}", doctorId))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        var doctorReviews = objectMapper.readValue(content, Review[].class);

        Assertions.assertEquals(2, doctorReviews.length);
    }
}
