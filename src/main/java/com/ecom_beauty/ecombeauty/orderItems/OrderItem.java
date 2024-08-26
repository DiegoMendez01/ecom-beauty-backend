package com.ecom_beauty.ecombeauty.orderItems;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.ecom_beauty.ecombeauty.orders.Order;
import com.ecom_beauty.ecombeauty.products.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be positive")
    @Column(nullable = false)
    private Integer quantity;

    @NotNull(message = "Price at time is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Price at time must be non-negative")
    @Column(name = "price_at_time", nullable = false, precision = 10, scale = 2)
    private BigDecimal priceAtTime;

    @NotNull(message = "Discount percentage at time is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Discount percentage at time must be at least 0")
    @DecimalMax(value = "100.0", inclusive = true, message = "Discount percentage at time must be at most 100")
    @Column(name = "discount_percentage_at_time", nullable = false, precision = 5, scale = 2)
    private BigDecimal discountPercentageAtTime;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}