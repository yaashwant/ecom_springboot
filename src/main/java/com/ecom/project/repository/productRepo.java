package com.ecom.project.repository;


import com.ecom.project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Repository
public interface productRepo extends JpaRepository<Product,Integer> {

   // @Query("SELECT p from Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%',:keyword, '%') )")
    Optional<Product> findByName(String name);

}
