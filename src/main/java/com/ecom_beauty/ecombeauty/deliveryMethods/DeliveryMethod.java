package com.ecom_beauty.ecombeauty.deliveryMethods;


import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "delivery_methods", indexes = @Index(columnList = "name", name = "delivery_methods_name_idx"))
public class DeliveryMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name is required")
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull(message = "Cost is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Cost must be non-negative")
    @Digits(integer = 8, fraction = 2, message = "Cost must have at most 8 integer digits and 2 fraction digits")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal cost;

    public DeliveryMethod(String name, BigDecimal cost) {
        this.name = name;
        this.cost = cost;
    }
}
