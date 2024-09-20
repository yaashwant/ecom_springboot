package com.ecom.project.service.Implementations;

import com.ecom.project.model.Product;
import com.ecom.project.model.Reviews;
import com.ecom.project.repository.reviewRepo;
import com.ecom.project.service.Operations.addReviewToProduct;
import com.ecom.project.service.Operations.getReviewByProduct;
import com.ecom.project.service.reviewService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class reviewServiceImpl implements reviewService, getReviewByProduct, addReviewToProduct {

    @Autowired
    private reviewRepo repo;


    public List<Reviews> getAllReviews() {
        return repo.findAll();
    }

    public Optional<Reviews> getReviewByBrand(String brand) {
        return repo.findByBrand(brand);
    }

    public Reviews addReview(Reviews reviews) {
        return repo.save(reviews);
    }


    public Reviews updateReviewByName(String name, Reviews updatedReview) {
        Reviews review = repo.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Review with name " + name + " not found"));
        review.setBrand(updatedReview.getBrand());
        review.setCategory(updatedReview.getCategory());
        review.setStars(updatedReview.getStars());
        review.setContent(updatedReview.getContent());
        return repo.save(review);
    }

    public void deleteReviewByName(String name) {
        Reviews review = repo.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Review with name " + name + " not found"));
        repo.delete(review);
    }

    @Override
    public List<Reviews> getReviewsByProduct(Product product) {
        return repo.findByProduct(product);
    }
    @Override
    public Reviews addReviewsToProduct(Product product, Reviews review) {
        review.setProduct(product);
        return repo.save(review);
    }
}
