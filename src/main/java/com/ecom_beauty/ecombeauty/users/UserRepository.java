package com.ecom_beauty.ecombeauty.users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<User> findByFirstName(String firstName);
    Optional<User> findByLastName(String lastName);
}