package com.ecom_beauty.ecombeauty.users;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(Integer id);
    public User getUserByEmail(String email);
    Optional<User> getUserByFirstName(String firstName);
    Optional<User> getUserByLastName(String lastName);
    User saveUser(User user);
    void deleteUser(Integer id);
    boolean existsByEmail(String email);
}