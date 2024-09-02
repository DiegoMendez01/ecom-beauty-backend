package com.ecom_beauty.ecombeauty.orders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> getAllOrders();
    Optional<Order> getOrderById(Integer id);
    List<Order> getOrdersByUserId(Integer userId);
    List<Order> getOrdersByStatus(OrderStatus status);
    List<Order> getOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    List<Order> getOrdersByTotalAmountGreaterThan(BigDecimal amount);
    List<Order> getOrdersByUserIdAndStatus(Integer userId, OrderStatus status);
    BigDecimal calculateTotalRevenueBetweenDates(LocalDateTime startDate, LocalDateTime endDate);
    List<Object[]> getOrderCountByDeliveryMethod();
    List<Order> getOrdersWithPromoCode();
    Order saveOrder(Order order);
    void deleteOrder(Integer id);
}