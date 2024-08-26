package com.ecom_beauty.ecombeauty.orderItems;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    List<OrderItem> findByOrderId(Integer orderId);
    
    List<OrderItem> findByProductId(Integer productId);
    
    @Query("SELECT oi.product.id, SUM(oi.quantity) FROM OrderItem oi GROUP BY oi.product.id ORDER BY SUM(oi.quantity) DESC")
    List<Object[]> findMostOrderedProducts();
    
    @Query("SELECT oi FROM OrderItem oi WHERE oi.order.id = :orderId AND oi.discountPercentageAtTime > 0")
    List<OrderItem> findDiscountedItemsInOrder(Integer orderId);
    
    @Query("SELECT AVG(oi.quantity) FROM OrderItem oi WHERE oi.product.id = :productId")
    Double calculateAverageQuantityOrderedForProduct(Integer productId);
    
    @Query("SELECT oi.product.id, AVG(oi.priceAtTime) FROM OrderItem oi GROUP BY oi.product.id")
    List<Object[]> calculateAveragePricePerProduct();
}