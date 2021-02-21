package com.salvatore.cinemates.services;

import com.salvatore.cinemates.dao.CinematesUserRepository;
import com.salvatore.cinemates.dao.ReviewRepository;
import com.salvatore.cinemates.dto.ReviewDto;
import com.salvatore.cinemates.model.Review;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository repository;

    @Autowired
    private CinematesUserRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger(ReviewService.class);

    public Boolean saveNewReview(ReviewDto reviewDto) {
        Review review = convertDtoToModel(reviewDto);
        try {
            review = repository.save(review);
            return Boolean.TRUE;
        } catch (IllegalArgumentException e) {
            logger.error(ExceptionUtils.getStackTrace(e));
            return Boolean.FALSE;
        }
    }

    public List<ReviewDto> getReviewsForMovie(int movieId) {
        List<Review> reviews = repository.findReviewByMovieId(movieId);
        List<ReviewDto> reviewDtos = new ArrayList<>();
        for (Review review: reviews) {
            reviewDtos.add(convertModelToDto(review));
        }
        return reviewDtos;
    }

    private Review convertDtoToModel(ReviewDto reviewDto) {
        Review review = null;
        if (reviewDto != null) {
            review = new Review();
            review.setDescription(reviewDto.getDescription());
            review.setRating(reviewDto.getRating());
            review.setTmdbMovieId(reviewDto.getMovieId());
            review.setUser(userRepository.findByUsername(reviewDto.getCinematesUserUsername()));
        }

        return review;
    }

    private ReviewDto convertModelToDto(Review review) {
        ReviewDto reviewDto = null;
        if (review != null) {
            reviewDto = new ReviewDto();
            reviewDto.setId(review.getReviewId());
            reviewDto.setMovieId(review.getTmdbMovieId());
            reviewDto.setDescription(review.getDescription());
            reviewDto.setCinematesUserUsername(review.getUser().getUsername());
        }

        return reviewDto;
    }
}
