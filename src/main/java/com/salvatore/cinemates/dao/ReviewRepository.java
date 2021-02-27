package com.salvatore.cinemates.dao;

import com.salvatore.cinemates.model.CinematesUser;
import com.salvatore.cinemates.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findReviewByCinematesUser(CinematesUser user);
    List<Review> findReviewByTmdbMovieId(int movieId);

}
