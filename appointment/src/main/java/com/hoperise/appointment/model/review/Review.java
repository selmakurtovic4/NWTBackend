package com.hoperise.appointment.model.review;

import com.hoperise.appointment.model.appointment.Appointment;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "rating")
    @NotNull(message = "Rating must be specified!")
    @Min(value = 1, message = "Rating must be at least 1!")
    @Max(value = 5, message = "Rating must be at most 5!")
    private Integer rating;
    @Column(name = "comment")
    @NotBlank(message = "Comment cannot be empty!")
    private String comment;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    public Review() {
    }

    public Review(Long id) {
        this.id = id;
    }

    public Review(Integer rating, String comment, Appointment appointment) {
        this.rating = rating;
        this.comment = comment;
        this.appointment = appointment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
}
