package com.hoperise.appointment.controller;

import com.hoperise.appointment.model.Review;
import com.hoperise.appointment.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/all")
    public @ResponseBody ResponseEntity<List<Review>> getAllReviews() {
        var reviews = reviewService.getAllReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<Review> getReview(@PathVariable Long id) {
        var review = reviewService.getReview(id);
        return  new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PostMapping("/add/{appointmentId}")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody ResponseEntity<Review> addReview(@Valid @RequestBody Review review, @PathVariable Long appointmentId) {
        var newReview = reviewService.addReview(review, appointmentId);
        return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public @ResponseBody ResponseEntity<Review> updateReview(@Valid @RequestBody Review review, @PathVariable Long id) {
        var newReview = reviewService.updateReview(review, id);
        return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<Map<String, String>> deleteReview(@PathVariable Long id) {
        return new ResponseEntity<>(reviewService.deleteReview(id), HttpStatus.OK);
    }

    @GetMapping("/getDoctorReviews/{doctorId}")
    public @ResponseBody ResponseEntity<List<Review>> getDoctorReviews(@PathVariable Long doctorId) {
        List<Review> doctorReviews = reviewService.getDoctorReviews(doctorId);
        return new ResponseEntity<>(doctorReviews, HttpStatus.OK);
    }
}
