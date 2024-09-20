package com.ecom.project.controller;

import com.ecom.project.model.Reviews;
import com.ecom.project.service.Implementations.reviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class reviewController {

    @Autowired
    private reviewServiceImpl service;

    @GetMapping("/review")
    public ResponseEntity<List<Reviews>>getAllReviews(){
        return new ResponseEntity<>(service.getAllReviews(), HttpStatus.FOUND);
    }

    @GetMapping("/review/brand/{brand}")
    public ResponseEntity<Optional<Reviews>> getReviewByBrand(@PathVariable String brand){
        return new ResponseEntity<>(service.getReviewByBrand(brand), HttpStatus.FOUND);
    }

    @PostMapping("/review/add")
    public ResponseEntity<Reviews> addReview(@RequestBody Reviews review){
        return new ResponseEntity<>(service.addReview(review), HttpStatus.CREATED);
    }
    @PutMapping("/review/name/{name}")
    public ResponseEntity<Reviews> updateReviewByName(@PathVariable String name, @RequestBody Reviews updatedReview){
        return new ResponseEntity<>(service.updateReviewByName(name,updatedReview), HttpStatus.OK);
    }


    @DeleteMapping("/review/name/{name}")
    public ResponseEntity<Reviews> deleteReviewByName(@PathVariable String name){
        service.deleteReviewByName(name);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
