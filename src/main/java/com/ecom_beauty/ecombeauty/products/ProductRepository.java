package com.ecom_beauty.ecombeauty.products;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategory_Id(Integer categoryId);
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
    List<Product> findByRatingGreaterThanEqual(BigDecimal rating);
    List<Product> findByStockGreaterThan(Integer stock);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Product> searchProducts(String keyword);

    @Query("SELECT p FROM Product p ORDER BY p.rating DESC, p.price ASC")
    List<Product> findTopRatedProducts();

    Optional<Product> findByNameIgnoreCase(String name);
    Optional<Product> findFirstByCategory_Id(Integer categoryId);
}