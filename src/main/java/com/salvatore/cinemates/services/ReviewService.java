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
    private CinematesUserService service;

    private final Logger logger = LoggerFactory.getLogger(ReviewService.class);
    private final String className = "com.salvatore.cinemates.services.ReviewService ";

    public Boolean saveNewReview(@NotNull ReviewDto reviewDto) {

//        Review review = convertDtoToModel(reviewDto);
//        try {
//            repository.save(review);
//            return Boolean.TRUE;
//        } catch (IllegalArgumentException e) {
//            logger.error(ExceptionUtils.getStackTrace(e));
//            return Boolean.FALSE;
//        } finally {
//            repository.flush();
//        }
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
            Optional<CinematesUser> userOptional = userRepository.findByUsername(username);
            if (userOptional.isPresent()) {
                List<Review> reviews = repository.findReviewByCinematesUser(userOptional.get());
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
            logger.info(this.className + "-- convert reviewDto to review");
            Review review = new Review();
            logger.info(this.className + "review");

        } else return null;
//        Review review = null;
//        if (reviewDto != null) {
//            Optional<CinematesUser> user = userRepository.findByUsername(reviewDto.getCinematesUserUsername());
//            if (user.isPresent()) {
//                review = new Review();
//                review.setDescription(reviewDto.getDescription());
//                review.setRating(reviewDto.getRating());
//                review.setTmdbMovieId(reviewDto.getMovieId());
//                review.setUser(user.get());
//            }
        }
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
