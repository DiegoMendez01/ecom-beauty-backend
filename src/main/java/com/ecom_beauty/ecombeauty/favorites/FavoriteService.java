package com.ecom_beauty.ecombeauty.favorites;

import java.util.List;
import java.util.Optional;

public interface FavoriteService {
    List<Favorite> getFavoritesByUserId(Integer userId);
    Optional<Favorite> getFavoriteByUserIdAndProductId(Integer userId, Integer productId);
    Favorite saveFavorite(Favorite favorite);
    void deleteFavorite(Integer favoriteId);
    void deleteFavoriteByUserIdAndProductId(Integer userId, Integer productId);
    boolean existsByUserIdAndProductId(Integer userId, Integer productId);
}