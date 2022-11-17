package com.ssafy.hw.model.dao;

import java.util.List;

import com.ssafy.hw.model.dto.Review;

public interface ReviewDao {

	int insertReview(Review review);

	int deleteReview(int reviewId);

	List<Review> selectAllReview(String pCode);
}



