package com.rohit.ElectronicStore.repositories;

import com.rohit.ElectronicStore.entities.Cart;
import com.rohit.ElectronicStore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, String> {


    Optional<Cart> findByUser(User user);

}

