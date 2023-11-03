package com.rohit.ElectronicStore.repositories;

import com.rohit.ElectronicStore.entities.Order;
import com.rohit.ElectronicStore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {

    List<Order> findByUser(User user);

}
