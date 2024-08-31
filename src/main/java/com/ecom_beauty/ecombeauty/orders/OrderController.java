package com.ecom_beauty.ecombeauty.orders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom_beauty.ecombeauty.orderStatus.OrderStatus;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer id) {
        return orderService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
    }

    @GetMapping("/status/{statusId}")
    public ResponseEntity<List<Order>> getOrdersByStatus(@PathVariable Integer statusId) {
        OrderStatus status = new OrderStatus();
        status.setId(statusId);
        return ResponseEntity.ok(orderService.getOrdersByStatus(status));
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<Order>> getOrdersByDateRange(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        return ResponseEntity.ok(orderService.getOrdersByDateRange(startDate, endDate));
    }

    @GetMapping("/amount-greater-than")
    public ResponseEntity<List<Order>> getOrdersByTotalAmountGreaterThan(@RequestParam BigDecimal amount) {
        return ResponseEntity.ok(orderService.getOrdersByTotalAmountGreaterThan(amount));
    }

    @GetMapping("/user/{userId}/status/{statusId}")
    public ResponseEntity<List<Order>> getOrdersByUserIdAndStatusId(
            @PathVariable Integer userId,
            @PathVariable Integer statusId) {
        return ResponseEntity.ok(orderService.getOrdersByUserIdAndStatusId(userId, statusId));
    }

    @GetMapping("/revenue")
    public ResponseEntity<BigDecimal> calculateTotalRevenueBetweenDates(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        return ResponseEntity.ok(orderService.calculateTotalRevenueBetweenDates(startDate, endDate));
    }

    @GetMapping("/delivery-method-count")
    public ResponseEntity<List<Object[]>> getOrderCountByDeliveryMethod() {
        return ResponseEntity.ok(orderService.getOrderCountByDeliveryMethod());
    }

    @GetMapping("/with-promo-code")
    public ResponseEntity<List<Order>> getOrdersWithPromoCode() {
        return ResponseEntity.ok(orderService.getOrdersWithPromoCode());
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.saveOrder(order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}

