package com.hoperise.appointment.model.review;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReviewRequest {

    @NotNull(message = "Rating must be specified!")
    @Min(value = 1, message = "Rating must be at least 1!")
    @Max(value = 5, message = "Rating must be at most 5!")
    private Integer rating;

    @NotBlank(message = "Comment cannot be empty!")
    private String comment;

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

}
