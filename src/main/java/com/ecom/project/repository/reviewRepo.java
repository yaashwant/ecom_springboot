package com.ecom.project.repository;

import com.ecom.project.model.Product;
import com.ecom.project.model.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface reviewRepo extends JpaRepository<Reviews,String> {

    Optional<Reviews> findByBrand(String brand);

    Optional<Reviews> findByName(String name);

    List<Reviews> findByProduct(Product product);

}
