package com.ecom_beauty.ecombeauty.paymentMethods;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Override
    public List<PaymentMethod> getAllPaymentMethods() {
        return paymentMethodRepository.findAll();
    }

    @Override
    public Optional<PaymentMethod> getPaymentMethodById(Integer id) {
        return paymentMethodRepository.findById(id);
    }

    @Override
    public Optional<PaymentMethod> getPaymentMethodByName(String name) {
        return paymentMethodRepository.findByName(name);
    }

    @Override
    public PaymentMethod savePaymentMethod(PaymentMethod paymentMethod) {
        return paymentMethodRepository.save(paymentMethod);
    }

    @Override
    public void deletePaymentMethod(Integer id) {
        paymentMethodRepository.deleteById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return paymentMethodRepository.existsByName(name);
    }
}