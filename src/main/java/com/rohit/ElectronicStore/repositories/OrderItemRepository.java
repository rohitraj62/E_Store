package com.rohit.ElectronicStore.repositories;

import com.rohit.ElectronicStore.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>
{
}

