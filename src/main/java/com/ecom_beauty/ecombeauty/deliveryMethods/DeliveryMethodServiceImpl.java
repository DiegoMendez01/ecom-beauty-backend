package com.ecom_beauty.ecombeauty.deliveryMethods;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryMethodServiceImpl implements DeliveryMethodService {

    @Autowired
    private DeliveryMethodRepository deliveryMethodRepository;

    @Override
    public List<DeliveryMethod> getAllDeliveryMethods() {
        return deliveryMethodRepository.findAll();
    }

    @Override
    public Optional<DeliveryMethod> getDeliveryMethodById(Integer id) {
        return deliveryMethodRepository.findById(id);
    }

    @Override
    public Optional<DeliveryMethod> getDeliveryMethodByName(String name) {
        return deliveryMethodRepository.findByName(name);
    }

    @Override
    public DeliveryMethod saveDeliveryMethod(DeliveryMethod deliveryMethod) {
        return deliveryMethodRepository.save(deliveryMethod);
    }

    @Override
    public void deleteDeliveryMethod(Integer id) {
        deliveryMethodRepository.deleteById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return deliveryMethodRepository.existsByName(name);
    }

    @Override
    public List<DeliveryMethod> getDeliveryMethodsByCostLessThanEqual(BigDecimal maxCost) {
        return deliveryMethodRepository.findByCostLessThanEqual(maxCost);
    }

    @Override
    public List<DeliveryMethod> getAllDeliveryMethodsOrderedByCost() {
        return deliveryMethodRepository.findAllByOrderByCostAsc();
    }
}