package com.ecom_beauty.ecombeauty.productReviews;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductReviewServiceImpl implements ProductReviewService {

    @Autowired
    private ProductReviewRepository productReviewRepository;

    @Override
    public List<ProductReview> getAllProductReviews() {
        return productReviewRepository.findAll();
    }

    @Override
    public Optional<ProductReview> getProductReviewById(Integer id) {
        return productReviewRepository.findById(id);
    }

    @Override
    public List<ProductReview> getProductReviewsByProductId(Integer productId) {
        return productReviewRepository.findByProductId(productId);
    }

    @Override
    public List<ProductReview> getProductReviewsByUserId(Integer userId) {
        return productReviewRepository.findByUserId(userId);
    }

    @Override
    public Optional<ProductReview> getProductReviewByProductIdAndUserId(Integer productId, Integer userId) {
        return productReviewRepository.findByProductIdAndUserId(productId, userId);
    }

    @Override
    public Double calculateAverageRatingForProduct(Integer productId) {
        return productReviewRepository.calculateAverageRatingForProduct(productId);
    }

    @Override
    public List<ProductReview> getProductReviewsSortedByDate(Integer productId) {
        return productReviewRepository.findByProductIdOrderByCreatedAtDesc(productId);
    }

    @Override
    public List<ProductReview> getHighRatedReviewsForProduct(Integer productId, Integer minRating) {
        return productReviewRepository.findHighRatedReviewsForProduct(productId, minRating);
    }

    @Override
    public ProductReview saveProductReview(ProductReview productReview) {
        return productReviewRepository.save(productReview);
    }

    @Override
    public void deleteProductReview(Integer id) {
        productReviewRepository.deleteById(id);
    }
}