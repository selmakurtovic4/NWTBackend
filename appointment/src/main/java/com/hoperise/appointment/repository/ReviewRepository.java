package com.hoperise.appointment.repository;

import com.hoperise.appointment.model.Review;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByAppointment_DoctorId(Long doctorId);
}
