package com.ecom_beauty.ecombeauty.paymentMethods;

import java.util.List;
import java.util.Optional;

public interface PaymentMethodService {
    List<PaymentMethod> getAllPaymentMethods();
    Optional<PaymentMethod> getPaymentMethodById(Integer id);
    Optional<PaymentMethod> getPaymentMethodByName(String name);
    PaymentMethod savePaymentMethod(PaymentMethod paymentMethod);
    void deletePaymentMethod(Integer id);
    boolean existsByName(String name);
}