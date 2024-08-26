package com.ecom_beauty.ecombeauty.orders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom_beauty.ecombeauty.orderStatus.OrderStatus;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> getOrderById(Integer id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> getOrdersByUserId(Integer userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status);
    }

    @Override
    public List<Order> getOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return orderRepository.findByCreatedAtBetween(startDate, endDate);
    }

    @Override
    public List<Order> getOrdersByTotalAmountGreaterThan(BigDecimal amount) {
        return orderRepository.findByTotalAmountGreaterThan(amount);
    }

    @Override
    public List<Order> getOrdersByUserIdAndStatusId(Integer userId, Integer statusId) {
        return orderRepository.findByUserIdAndStatusId(userId, statusId);
    }

    @Override
    public BigDecimal calculateTotalRevenueBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return orderRepository.calculateTotalRevenueBetweenDates(startDate, endDate);
    }

    @Override
    public List<Object[]> getOrderCountByDeliveryMethod() {
        return orderRepository.countOrdersByDeliveryMethod();
    }

    @Override
    public List<Order> getOrdersWithPromoCode() {
        return orderRepository.findOrdersWithPromoCode();
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }
}