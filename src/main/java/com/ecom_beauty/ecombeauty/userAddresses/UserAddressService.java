package com.ecom_beauty.ecombeauty.userAddresses;

import java.util.List;
import java.util.Optional;

public interface UserAddressService {
    List<UserAddress> getAllUserAddresses();
    Optional<UserAddress> getUserAddressById(Integer id);
    List<UserAddress> getUserAddressesByUserId(Integer userId);
    Optional<UserAddress> getDefaultAddressForUser(Integer userId);
    List<UserAddress> getUserAddressesSortedByDefaultAndDate(Integer userId);
    UserAddress saveUserAddress(UserAddress userAddress);
    void deleteUserAddress(Integer id);
    void deleteUserAddressByUserIdAndAddressId(Integer userId, Integer addressId);
    UserAddress setDefaultAddress(Integer userId, Integer addressId);
}