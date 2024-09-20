package com.ecom.project.service.Operations;

import com.ecom.project.model.Product;
import com.ecom.project.model.Reviews;

import java.util.List;

@FunctionalInterface
public interface getReviewByProduct {
    public List<Reviews> getReviewsByProduct(Product product);

}
