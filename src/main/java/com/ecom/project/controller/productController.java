package com.ecom.project.controller;

import com.ecom.project.model.Product;
import com.ecom.project.model.Reviews;
import com.ecom.project.service.Implementations.productServiceImpl;
import com.ecom.project.service.Implementations.reviewServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class productController {

    @RequestMapping("/")
    public String greetings(HttpServletRequest request) {
        return "Welcome to Ecom Project " + request.getSession().getId();
    }

//    @GetMapping("/csrf")
//    public CsrfToken getCsrfToken(HttpServletRequest request) {
//        return (CsrfToken) request.getAttribute("_csrf");
//    }

    @Autowired
    private productServiceImpl service;

    @Autowired
    private reviewServiceImpl reviewService;

    @GetMapping("/product")
    public ResponseEntity<List<Product>> getAllProducts(){

        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.FOUND);
    }

    //Getting the details of the product by posting an id

    @PostMapping("/productbyid")
    public ResponseEntity<Product> getProductById(@RequestBody Map<String,Integer> Id) {
        int productId =Id.get("Id");
        Product productN = service.getProductById(productId);
        if(productN != null)
            return new ResponseEntity<>(productN, HttpStatus.FOUND);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping ("/product/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return new ResponseEntity<>(service.addProduct(product), HttpStatus.CREATED);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {
        return new ResponseEntity<>(service.updateProduct(product), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable int id) {
        System.out.println("deleting the"+id);
        return new ResponseEntity<>( service.deleteProduct(id), HttpStatus.GONE);
    }

    @GetMapping("/product/search/{name}")
    public ResponseEntity<Optional<Product>> searchProduct(@PathVariable String name){
        System.out.println("searching the"+name);
        return new ResponseEntity<>(service.searchProduct(name),HttpStatus.OK);
    }

    //Get reviews for a product
    @GetMapping("/product/reviews/{id}")
    public ResponseEntity<List<Reviews>>getProductReviews(@PathVariable int id){
        Product product = service.getProductById(id);
        if(product != null){
            List<Reviews> reviews = reviewService.getReviewsByProduct(product);
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Add a review to a product
    @PostMapping("/product/{id}/reviews")
    public ResponseEntity<Reviews> addReviewToProduct(@PathVariable int id, @RequestBody Reviews review) {
        Product product = service.getProductById(id);
        if (product != null) {
            Reviews savedReview = reviewService.addReviewsToProduct(product, review);
            return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
