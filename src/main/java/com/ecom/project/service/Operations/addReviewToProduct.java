package com.ecom.project.service.Operations;

import com.ecom.project.model.Product;
import com.ecom.project.model.Reviews;


@FunctionalInterface
public interface addReviewToProduct {
    public Reviews addReviewsToProduct(Product product, Reviews review);
}
