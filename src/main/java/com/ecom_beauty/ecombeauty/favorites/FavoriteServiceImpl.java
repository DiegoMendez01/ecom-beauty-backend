package com.ecom_beauty.ecombeauty.favorites;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Override
    public List<Favorite> getFavoritesByUserId(Integer userId) {
        return favoriteRepository.findByUserId(userId);
    }

    @Override
    public Optional<Favorite> getFavoriteByUserIdAndProductId(Integer userId, Integer productId) {
        return favoriteRepository.findByUserIdAndProductId(userId, productId);
    }

    @Override
    public Favorite saveFavorite(Favorite favorite) {
        return favoriteRepository.save(favorite);
    }

    @Override
    public void deleteFavorite(Integer favoriteId) {
        favoriteRepository.deleteById(favoriteId);
    }

    @Override
    @Transactional
    public void deleteFavoriteByUserIdAndProductId(Integer userId, Integer productId) {
        favoriteRepository.deleteByUserIdAndProductId(userId, productId);
    }

    @Override
    public boolean existsByUserIdAndProductId(Integer userId, Integer productId) {
        return favoriteRepository.existsByUserIdAndProductId(userId, productId);
    }
}