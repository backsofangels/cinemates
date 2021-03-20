package com.salvatore.cinemates;

import com.salvatore.cinemates.dto.ReviewDto;
import com.salvatore.cinemates.services.ReviewService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestReviewService {
    @Autowired
    private ReviewService reviewService;

    @Test
    void testSaveNewReview() {
        ReviewDto reviewDto = new ReviewDto(1, "bello", 1, "backsofangels");
        Assertions.assertTrue(reviewService.saveNewReview(reviewDto));
    }

    @Test
    void testGetReviewsForMovie() {

    }
}
