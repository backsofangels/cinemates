package com.salvatore.cinemates.controller;

import com.salvatore.cinemates.dto.ReviewDto;
import com.salvatore.cinemates.model.Review;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReviewController {

    @RequestMapping(method = RequestMethod.POST, value = "/review/post")
    public ResponseEntity<?> postReviewForMovie(ReviewDto review) {
        return ResponseEntity.ok(review);
    }

    /*
    @RequestMapping(method = RequestMethod.GET, value ="review/getForMovie")
    public ResponseEntity<List<ReviewDto>> getReviewsForMovie(int movieId) {

    }
     */


}
