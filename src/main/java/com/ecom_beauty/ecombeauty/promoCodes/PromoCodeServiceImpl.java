package com.ecom_beauty.ecombeauty.promoCodes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PromoCodeServiceImpl implements PromoCodeService {

    @Autowired
    private PromoCodeRepository promoCodeRepository;

    @Override
    public List<PromoCode> getAllPromoCodes() {
        return promoCodeRepository.findAll();
    }

    @Override
    public Optional<PromoCode> getPromoCodeById(Integer id) {
        return promoCodeRepository.findById(id);
    }

    @Override
    public Optional<PromoCode> getPromoCodeByCode(String code) {
        return promoCodeRepository.findByCode(code);
    }

    @Override
    public Optional<PromoCode> getValidPromoCode(String code) {
        return promoCodeRepository.findValidPromoCode(code, LocalDateTime.now());
    }

    @Override
    public List<PromoCode> getActivePromoCodes(LocalDateTime from, LocalDateTime to) {
        return promoCodeRepository.findByValidFromLessThanEqualAndValidUntilGreaterThanEqual(from, to);
    }

    @Override
    public List<PromoCode> getExpiredPromoCodes(LocalDateTime date) {
        return promoCodeRepository.findByValidUntilLessThan(date);
    }

    @Override
    public List<PromoCode> getExhaustedPromoCodes() {
        return promoCodeRepository.findExhaustedPromoCodes();
    }

    @Override
    public PromoCode savePromoCode(PromoCode promoCode) {
        return promoCodeRepository.save(promoCode);
    }

    @Override
    public void deletePromoCode(Integer id) {
        promoCodeRepository.deleteById(id);
    }

    @Override
    public boolean isPromoCodeValid(String code) {
        return getValidPromoCode(code).isPresent();
    }

    @Override
    @Transactional
    public boolean applyPromoCode(String code) {
        Optional<PromoCode> validPromoCodeOpt = getValidPromoCode(code);
        if (validPromoCodeOpt.isPresent()) {
            PromoCode promoCode = validPromoCodeOpt.get();
            promoCode.setCurrentUsageCount(promoCode.getCurrentUsageCount() + 1);
            promoCodeRepository.save(promoCode);
            return true;
        }
        return false;
    }
}