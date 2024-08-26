package com.ecom_beauty.ecombeauty.promoCodes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PromoCodeRepository extends JpaRepository<PromoCode, Integer> {
    Optional<PromoCode> findByCode(String code);

    @Query("SELECT p FROM PromoCode p WHERE p.code = :code AND p.validFrom <= :now AND (p.validUntil IS NULL OR p.validUntil >= :now) AND (p.usageLimit IS NULL OR p.currentUsageCount < p.usageLimit)")
    Optional<PromoCode> findValidPromoCode(String code, LocalDateTime now);

    List<PromoCode> findByValidFromLessThanEqualAndValidUntilGreaterThanEqual(LocalDateTime from, LocalDateTime to);

    List<PromoCode> findByValidUntilLessThan(LocalDateTime date);

    @Query("SELECT p FROM PromoCode p WHERE p.usageLimit IS NOT NULL AND p.currentUsageCount >= p.usageLimit")
    List<PromoCode> findExhaustedPromoCodes();
}