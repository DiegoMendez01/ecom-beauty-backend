package com.ecom_beauty.ecombeauty.cartItems;

import java.util.List;
import java.util.Optional;

public interface CartItemService {
    List<CartItem> getCartItemsByCartId(Integer cartId);
    Optional<CartItem> getCartItemByCartIdAndProductId(Integer cartId, Integer productId);
    void deleteCartItemsByCartId(Integer cartId);
    CartItem saveCartItem(CartItem cartItem);
    void deleteCartItem(Integer cartItemId);
}