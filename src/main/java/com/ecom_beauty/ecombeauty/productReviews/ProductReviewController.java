package com.ecom_beauty.ecombeauty.productReviews;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/product-reviews")
public class ProductReviewController {

    @Autowired
    private ProductReviewService productReviewService;

    @PostMapping
    public ResponseEntity<ProductReview> createProductReview(@RequestBody ProductReview productReview) {
        ProductReview createdReview = productReviewService.saveProductReviewAndUpdateRating(productReview);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductReview> getProductReviewById(@PathVariable Integer id) {
        Optional<ProductReview> productReview = productReviewService.getProductReviewById(id);
        return ResponseEntity.ok(productReview.get());
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ProductReview>> getProductReviewsByProductId(@PathVariable Integer productId) {
        List<ProductReview> reviews = productReviewService.getProductReviewsByProductId(productId);
        return ResponseEntity.ok(reviews);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductReview> updateProductReview(@PathVariable Integer id, @RequestBody ProductReview productReview) {
        ProductReview updatedReview = productReviewService.saveProductReviewAndUpdateRating(productReview);
        return ResponseEntity.ok(updatedReview);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductReview(@PathVariable Integer id) {
        productReviewService.deleteProductReview(id);
        return ResponseEntity.noContent().build();
    }
}
