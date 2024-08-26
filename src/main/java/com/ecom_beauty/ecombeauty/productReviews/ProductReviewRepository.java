package com.ecom_beauty.ecombeauty.productReviews;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductReviewRepository extends JpaRepository<ProductReview, Integer> {
    List<ProductReview> findByProductId(Integer productId);
    List<ProductReview> findByUserId(Integer userId);
    Optional<ProductReview> findByProductIdAndUserId(Integer productId, Integer userId);
    
    @Query("SELECT AVG(pr.rating) FROM ProductReview pr WHERE pr.product.id = :productId")
    Double calculateAverageRatingForProduct(Integer productId);
    
    List<ProductReview> findByProductIdOrderByCreatedAtDesc(Integer productId);
    
    @Query("SELECT pr FROM ProductReview pr WHERE pr.product.id = :productId AND pr.rating >= :minRating")
    List<ProductReview> findHighRatedReviewsForProduct(Integer productId, Integer minRating);
}
