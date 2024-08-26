package com.ecom_beauty.ecombeauty.productReviews;

import java.util.List;
import java.util.Optional;

public interface ProductReviewService {
    List<ProductReview> getAllProductReviews();
    Optional<ProductReview> getProductReviewById(Integer id);
    List<ProductReview> getProductReviewsByProductId(Integer productId);
    List<ProductReview> getProductReviewsByUserId(Integer userId);
    Optional<ProductReview> getProductReviewByProductIdAndUserId(Integer productId, Integer userId);
    Double calculateAverageRatingForProduct(Integer productId);
    List<ProductReview> getProductReviewsSortedByDate(Integer productId);
    List<ProductReview> getHighRatedReviewsForProduct(Integer productId, Integer minRating);
    ProductReview saveProductReview(ProductReview productReview);
    void deleteProductReview(Integer id);
}