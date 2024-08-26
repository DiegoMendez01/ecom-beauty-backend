package com.ecom_beauty.ecombeauty.deliveryMethods;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface DeliveryMethodService {
    List<DeliveryMethod> getAllDeliveryMethods();
    Optional<DeliveryMethod> getDeliveryMethodById(Integer id);
    Optional<DeliveryMethod> getDeliveryMethodByName(String name);
    DeliveryMethod saveDeliveryMethod(DeliveryMethod deliveryMethod);
    void deleteDeliveryMethod(Integer id);
    boolean existsByName(String name);
    List<DeliveryMethod> getDeliveryMethodsByCostLessThanEqual(BigDecimal maxCost);
    List<DeliveryMethod> getAllDeliveryMethodsOrderedByCost();
}