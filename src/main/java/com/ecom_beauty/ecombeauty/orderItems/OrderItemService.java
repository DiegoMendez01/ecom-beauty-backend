package com.ecom_beauty.ecombeauty.orderItems;

import java.util.List;
import java.util.Optional;

public interface OrderItemService {
    List<OrderItem> getAllOrderItems();
    Optional<OrderItem> getOrderItemById(Integer id);
    List<OrderItem> getOrderItemsByOrderId(Integer orderId);
    List<OrderItem> getOrderItemsByProductId(Integer productId);
    List<Object[]> getMostOrderedProducts();
    List<OrderItem> getDiscountedItemsInOrder(Integer orderId);
    Double calculateAverageQuantityOrderedForProduct(Integer productId);
    List<Object[]> calculateAveragePricePerProduct();
    OrderItem saveOrderItem(OrderItem orderItem);
    void deleteOrderItem(Integer id);
}