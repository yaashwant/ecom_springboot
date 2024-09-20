package com.ecom.project.service;

import com.ecom.project.model.Product;
import com.ecom.project.model.Reviews;

import java.util.List;

public interface reviewService {
    public List<Reviews> getAllReviews();
    public Reviews addReview(Reviews reviews);
    public Reviews updateReviewByName(String name, Reviews updatedReview);
    public void deleteReviewByName(String name);





}
