package com.ssafy.hw.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.hw.model.dao.ReviewDao;
import com.ssafy.hw.model.dto.Review;

@Service
public class ReviewServiceImpl implements ReviewService{
	 @Autowired
	 ReviewDao reviewDao;

	@Override
	public void insertReview(Review review) {
		reviewDao.insertReview(review);
	}

	@Override
	public void deleteReview(int reviewId) {
		reviewDao.deleteReview(reviewId);
	}

	@Override
	public List<Review> selectAllReview(String pCode) {
		return reviewDao.selectAllReview(pCode);
	}
	

}
