package com.ecom_beauty.ecombeauty.carts;

import java.util.Optional;

public interface CartService {
    Optional<Cart> getCartByUserId(Integer userId);
    void deleteCartByUserId(Integer userId);
    Cart saveCart(Cart cart);
}
