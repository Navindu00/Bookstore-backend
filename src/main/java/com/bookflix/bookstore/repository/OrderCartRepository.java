package com.bookflix.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookflix.bookstore.entity.OrderCart;

@Repository
public interface OrderCartRepository extends JpaRepository<OrderCart, Long>{
    
}
