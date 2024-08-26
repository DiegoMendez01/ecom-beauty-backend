package com.ecom_beauty.ecombeauty.deliveryMethods;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryMethodRepository extends JpaRepository<DeliveryMethod, Integer> {
    Optional<DeliveryMethod> findByName(String name);
    boolean existsByName(String name);
    List<DeliveryMethod> findByCostLessThanEqual(BigDecimal maxCost);
    List<DeliveryMethod> findAllByOrderByCostAsc();
}
