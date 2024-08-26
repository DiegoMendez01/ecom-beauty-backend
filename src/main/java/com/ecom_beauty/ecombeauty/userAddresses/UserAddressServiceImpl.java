package com.ecom_beauty.ecombeauty.userAddresses;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserAddressServiceImpl implements UserAddressService {

    @Autowired
    private UserAddressRepository userAddressRepository;

    @Override
    public List<UserAddress> getAllUserAddresses() {
        return userAddressRepository.findAll();
    }

    @Override
    public Optional<UserAddress> getUserAddressById(Integer id) {
        return userAddressRepository.findById(id);
    }

    @Override
    public List<UserAddress> getUserAddressesByUserId(Integer userId) {
        return userAddressRepository.findByUserId(userId);
    }

    @Override
    public Optional<UserAddress> getDefaultAddressForUser(Integer userId) {
        return userAddressRepository.findByUserIdAndIsDefaultTrue(userId);
    }

    @Override
    public List<UserAddress> getUserAddressesSortedByDefaultAndDate(Integer userId) {
        return userAddressRepository.findByUserIdOrderByIsDefaultDescCreatedAtDesc(userId);
    }

    @Override
    public UserAddress saveUserAddress(UserAddress userAddress) {
        return userAddressRepository.save(userAddress);
    }

    @Override
    public void deleteUserAddress(Integer id) {
        userAddressRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteUserAddressByUserIdAndAddressId(Integer userId, Integer addressId) {
        userAddressRepository.deleteByUserIdAndId(userId, addressId);
    }

    @Override
    @Transactional
    public UserAddress setDefaultAddress(Integer userId, Integer addressId) {
        // First, set all addresses for the user to non-default
        List<UserAddress> userAddresses = getUserAddressesByUserId(userId);
        for (UserAddress address : userAddresses) {
            address.setDefault(false);
            saveUserAddress(address);
        }

        // Then, set the specified address as default
        Optional<UserAddress> addressToSetDefault = getUserAddressById(addressId);
        if (addressToSetDefault.isPresent()) {
            UserAddress address = addressToSetDefault.get();
            address.setDefault(true);
            return saveUserAddress(address);
        }
        return null;
    }
}