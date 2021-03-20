package com.salvatore.cinemates.controller;

import java.util.List;

import com.salvatore.cinemates.dto.ReviewDto;
import com.salvatore.cinemates.services.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @RequestMapping(method = RequestMethod.POST, value = "/review/post")
    public ResponseEntity<?> postReviewForMovie(ReviewDto review) {
        boolean saved = reviewService.saveNewReview(review);
        if (saved) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(500).build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/review/getForMovie")
    public List<ReviewDto> getReviewsForMovie(int movieId) {
        return reviewService.getReviewsForMovie(movieId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/review/getForUser")
    public List<ReviewDto> getReviewsForUser(String username) {
        return reviewService.getReviewsForUser(username);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/review/updateReview")
    public ResponseEntity<?> updateReview(ReviewDto reviewDto) {
        boolean updated = reviewService.updateReview(reviewDto);
        if (updated) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(500).build();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/review/deleteReview")
    public ResponseEntity<?> deleteReview(ReviewDto reviewDto) {
        boolean deleted = reviewService.deleteReview(reviewDto);
        if (deleted) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(500).build();
    }
}
