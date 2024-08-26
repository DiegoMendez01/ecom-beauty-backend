package com.ecom_beauty.ecombeauty.orderItems;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    @Override
    public Optional<OrderItem> getOrderItemById(Integer id) {
        return orderItemRepository.findById(id);
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(Integer orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }

    @Override
    public List<OrderItem> getOrderItemsByProductId(Integer productId) {
        return orderItemRepository.findByProductId(productId);
    }

    @Override
    public List<Object[]> getMostOrderedProducts() {
        return orderItemRepository.findMostOrderedProducts();
    }

    @Override
    public List<OrderItem> getDiscountedItemsInOrder(Integer orderId) {
        return orderItemRepository.findDiscountedItemsInOrder(orderId);
    }

    @Override
    public Double calculateAverageQuantityOrderedForProduct(Integer productId) {
        return orderItemRepository.calculateAverageQuantityOrderedForProduct(productId);
    }

    @Override
    public List<Object[]> calculateAveragePricePerProduct() {
        return orderItemRepository.calculateAveragePricePerProduct();
    }

    @Override
    public OrderItem saveOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public void deleteOrderItem(Integer id) {
        orderItemRepository.deleteById(id);
    }
}