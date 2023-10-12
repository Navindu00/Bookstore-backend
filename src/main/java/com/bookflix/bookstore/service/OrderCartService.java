package com.bookflix.bookstore.service;

import org.springframework.stereotype.Service;

import com.bookflix.bookstore.dto.OrderCartDTO;
import com.bookflix.bookstore.entity.OrderCart;

@Service
public interface OrderCartService {
    OrderCartDTO createOrder(OrderCart order, String jwt);
    // Order updateBook(Long id, Order order);
}
