package com.iade.mobilemechanics.controllers;

import com.iade.mobilemechanics.models.Model;
import com.iade.mobilemechanics.models.Review;
import com.iade.mobilemechanics.models.exceptions.NotFoundException;
import com.iade.mobilemechanics.models.repositories.ReviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/reviews")
public class ReviewController {
    private final Logger logger = LoggerFactory.getLogger(ReviewController.class);

    @Autowired
    private ReviewRepository reviewRepository;
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Review> getReviews(){
        logger.info("Send all reviews to Request");
        return reviewRepository.findAll();
    }
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Review getReview(@PathVariable int id){
        logger.info("Send review with id "+ id + "to Request");
        Optional<Review> _review = reviewRepository.findById(id);
        if (!_review.isPresent()) throw
                new NotFoundException("" + id, "review", "id");
        else
            return _review.get();
    }
    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Review saveReview(@RequestBody Review review){
        Review saveReview = reviewRepository.save(review);
        logger.info("Save review id " + saveReview.getId() + " to Database");
        return saveReview;
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteReview(@PathVariable int id){
        logger.info("Delete review with id "+ id + "to Request");
        Optional<Review> _review = reviewRepository.findById(id);
        if (!_review.isPresent()) throw
                new NotFoundException("" + id, "review", "id");
        else{
            reviewRepository.deleteById(id);
            return "Deleted";}
    }
    @GetMapping(path = "/repair/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Review> getReviewByRepairId(@PathVariable int id){
        return reviewRepository.findByReviewRepairId(id);
    }
}
