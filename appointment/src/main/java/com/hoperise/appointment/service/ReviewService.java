package com.hoperise.appointment.service;

import com.hoperise.appointment.model.appointment.Appointment;
import com.hoperise.appointment.model.review.Review;
import com.hoperise.appointment.model.review.ReviewRequest;
import com.hoperise.appointment.repository.AppointmentRepository;
import com.hoperise.appointment.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review getReview(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review with ID " + id + " does not exist!"));
    }

    public Review addReview(ReviewRequest review, Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new EntityNotFoundException("Appointment with ID " + appointmentId + " does not exist!"));

        Review newReview = new Review();
        newReview.setRating(review.getRating());
        newReview.setComment(review.getComment());
        newReview.setAppointment(appointment);
        reviewRepository.save(newReview);
        return newReview;
    }

    public Review updateReview(ReviewRequest review, Long id) {
        Review existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review with ID " + id + " does not exist!"));

        existingReview.setRating(review.getRating());
        existingReview.setComment(review.getComment());
        return reviewRepository.save(existingReview);
    }

    public Map<String, String> deleteReview(Long id) {
        reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review with ID " + id + " does not exist!"));
        reviewRepository.deleteById(id);

        return Collections.singletonMap("message", "Review with ID " + id + " is successfully deleted!");
    }

    public List<Review> getDoctorReviews(Long doctorId) {
        List<Review> reviews = reviewRepository.findAllByAppointmentDoctorId(doctorId);
        if (reviews.isEmpty()) {
            throw new EntityNotFoundException("No reviews found for doctor with ID " + doctorId);
        }
        return reviews;
    }
}
