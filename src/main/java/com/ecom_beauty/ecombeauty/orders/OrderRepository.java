package com.ecom_beauty.ecombeauty.orders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecom_beauty.ecombeauty.orderStatus.OrderStatus;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUserId(Integer userId);
    List<Order> findByStatus(OrderStatus status);
    List<Order> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Order> findByTotalAmountGreaterThan(BigDecimal amount);
    
    @Query("SELECT o FROM Order o WHERE o.user.id = :userId AND o.status.id = :statusId")
    List<Order> findByUserIdAndStatusId(Integer userId, Integer statusId);
    
    @Query("SELECT SUM(o.totalAmount) FROM Order o WHERE o.createdAt BETWEEN :startDate AND :endDate")
    BigDecimal calculateTotalRevenueBetweenDates(LocalDateTime startDate, LocalDateTime endDate);
    
    @Query("SELECT o.deliveryMethod.name, COUNT(o) FROM Order o GROUP BY o.deliveryMethod.name")
    List<Object[]> countOrdersByDeliveryMethod();
    
    @Query("SELECT o FROM Order o WHERE o.promoCode IS NOT NULL")
    List<Order> findOrdersWithPromoCode();
}