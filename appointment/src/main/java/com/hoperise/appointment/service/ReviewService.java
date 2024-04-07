package com.hoperise.appointment.service;

import com.hoperise.appointment.model.appointment.Appointment;
import com.hoperise.appointment.model.Review;
import com.hoperise.appointment.repository.AppointmentRepository;
import com.hoperise.appointment.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        var exception = new EntityNotFoundException("Review with id " + id + " does not exist!");
        return reviewRepository.findById(id).orElseThrow(() -> exception);
    }

    public Review addReview(Review review, Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new EntityNotFoundException("Appointment with id " + appointmentId + " does not exist!"));

        Review newReview = new Review();
        newReview.setRating(review.getRating());
        newReview.setComment(review.getComment());
        newReview.setAppointment(appointment);
        reviewRepository.save(newReview);
        return newReview;
    }

    public Review updateReview(Review review, Long id) {
        var exception = new EntityNotFoundException("Review with id " + id + " does not exist!");
        Review newReview = reviewRepository.findById(id).orElseThrow(() -> exception);
        newReview.setRating(review.getRating());
        newReview.setComment(review.getComment());
        return reviewRepository.save(newReview);
    }

    public String deleteReview(Long id) {
        var exception = new EntityNotFoundException("Review with id " + id + " does not exist!");
        reviewRepository.findById(id).orElseThrow(() -> exception);
        reviewRepository.deleteById(id);
        return "Review with id " + id + " is successfully deleted!";
    }

    public List<Review> getDoctorReviews(Long doctorId) {
        return reviewRepository.findAllByAppointment_DoctorId(doctorId);
    }
}
