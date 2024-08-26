package com.ecom_beauty.ecombeauty.orderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderStatusService {
    List<OrderStatus> getAllOrderStatuses();
    Optional<OrderStatus> getOrderStatusById(Integer id);
    Optional<OrderStatus> getOrderStatusByName(String name);
    OrderStatus saveOrderStatus(OrderStatus orderStatus);
    void deleteOrderStatus(Integer id);
    boolean existsByName(String name);
}