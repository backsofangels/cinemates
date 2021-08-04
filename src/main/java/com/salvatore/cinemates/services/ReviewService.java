package com.salvatore.cinemates.services;

import com.salvatore.cinemates.dao.ReviewRepository;
import com.salvatore.cinemates.dto.ReviewDto;
import com.salvatore.cinemates.model.CinematesUser;
import com.salvatore.cinemates.model.Review;
import com.sun.istack.NotNull;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository repository;

    @Autowired
    private CinematesUserService cinematesUserService;

    private final Logger logger = LoggerFactory.getLogger(ReviewService.class);
    private final String className = "com.salvatore.cinemates.services.ReviewService";

    public Boolean saveNewReview(@NotNull ReviewDto reviewDto) {
        Review review = convertDtoToModel(reviewDto);
        try {
            if (review != null) {
                repository.save(review);
                repository.flush();
                return Boolean.TRUE;
            } else {
                logger.error(this.className + "-- review convertita nulla");
            }
        } catch (IllegalArgumentException e) {
            logger.error(ExceptionUtils.getMessage(e));
            repository.flush();
        }
        return Boolean.FALSE;
    }

    public List<ReviewDto> getReviewsForMovie(int movieId) {
        List<Review> reviews = repository.findReviewByTmdbMovieId(movieId);
        List<ReviewDto> reviewDtos = new ArrayList<>();
        for (Review review: reviews) {
            reviewDtos.add(convertModelToDto(review));
        }
        return reviewDtos;
    }

    public List<ReviewDto> getReviewsForUser(String username) {
        List<ReviewDto> reviewDtos = new ArrayList<>();

        try {
            CinematesUser user = cinematesUserService.findUser(username, CinematesUserService.SearchMode.USERNAME);
            if (user != null) {
                List<Review> reviews = repository.findReviewByCinematesUser(user);
                for (Review r: reviews) {
                    reviewDtos.add(convertModelToDto(r));
                }
            }
        } catch (IllegalArgumentException e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }

        return reviewDtos;
    }

    public Boolean updateReview(ReviewDto updatedReviewDto) {
        try {
            Review updatedReview = convertDtoToModel(updatedReviewDto);
            repository.save(updatedReview);
            return Boolean.TRUE;
        } catch (IllegalArgumentException e) {
            logger.error(ExceptionUtils.getStackTrace(e));
            return Boolean.FALSE;
        } finally {
            repository.flush();
        }
    }

    public Boolean deleteReview (ReviewDto deletedReviewDto) {
        try {
            Review deletedReview = convertDtoToModel(deletedReviewDto);
            repository.delete(deletedReview);
            return Boolean.TRUE;
        } catch (IllegalArgumentException e) {
            logger.error(ExceptionUtils.getStackTrace(e));
            return Boolean.FALSE;
        } finally {
            repository.flush();
        }
    }

    private Review convertDtoToModel(ReviewDto reviewDto) {
        if (reviewDto != null) {
            logger.info(this.className  + "-- converting DTO to Model");
            CinematesUser reviewUser = this.cinematesUserService.findUser(reviewDto.getCinematesUserUsername(), CinematesUserService.SearchMode.USERNAME);
            if (reviewUser != null) {
                Review review = new Review();
                review.setUser(reviewUser);
                review.setTmdbMovieId(reviewDto.getMovieId());
                review.setRating(reviewDto.getRating());
                review.setDescription(reviewDto.getDescription());
                return review;
            } else return null;
        } else return null;
    }

    private ReviewDto convertModelToDto(Review review) {

        if (review != null) {
            ReviewDto reviewDto = null;
            reviewDto = new ReviewDto();
            reviewDto.setId(review.getReviewId());
            reviewDto.setMovieId(review.getTmdbMovieId());
            reviewDto.setDescription(review.getDescription());
            reviewDto.setCinematesUserUsername(review.getUser().getUsername());
            reviewDto.setRating(review.getRating());
            return reviewDto;
        } else return null;
    }
}
