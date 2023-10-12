package com.bookflix.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bookflix.bookstore.entity.OrderCartItem;

public interface OrderCartItemRepository extends JpaRepository<OrderCartItem, Long>{
    
}
