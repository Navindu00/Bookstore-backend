package com.bookflix.bookstore.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookflix.bookstore.dto.OrderCartDTO;
import com.bookflix.bookstore.entity.Book;
import com.bookflix.bookstore.entity.OrderCart;
import com.bookflix.bookstore.entity.OrderCartItem;
import com.bookflix.bookstore.entity.User;
import com.bookflix.bookstore.exception.OutofStockException;
import com.bookflix.bookstore.repository.BookRepository;
import com.bookflix.bookstore.repository.OrderCartItemRepository;
import com.bookflix.bookstore.repository.OrderCartRepository;
import com.bookflix.bookstore.repository.UserRepository;
import com.bookflix.bookstore.security.jwt.JwtUtils;
import com.bookflix.bookstore.service.OrderCartService;



@Service

public class OrderCartServiceImpl implements OrderCartService{
    
    @Autowired
    private OrderCartRepository orderRepository;

    @Autowired
    private OrderCartItemRepository orderCartItemRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private JwtUtils jwtUtils;
    
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public OrderCartDTO createOrder(OrderCart orderCart, String jwt) {
        
        Double total = 0.0;

        for (OrderCartItem orderCartItem : orderCart.getOrderCartItems()) {
            Double subTotal = orderCartItem.getBook().getPrice() * orderCartItem.getCartQuantity();
            orderCartItem.setOrderCart(orderCart);
            orderCartItem.setSubTotal(subTotal);
            orderCartItemRepository.save(orderCartItem);
            total += subTotal ;
        }

        for (OrderCartItem orderCartItem : orderCart.getOrderCartItems()) {
            Book book = orderCartItem.getBook();
            if(book.getQuantity() < orderCartItem.getCartQuantity()){
                throw new OutofStockException("Out of stock!"); 
            }

            book.setQuantity(book.getQuantity() - orderCartItem.getCartQuantity());
            bookRepository.save(book);
        }
        
        String username = jwtUtils.extractUsername(jwt.substring(7));
        
        User user = userRepository.findByUsername(username).orElseThrow();
        orderCart.setTotalPrice(total);
        orderCart.setUser(user);
        OrderCart orderSaved = orderRepository.save(orderCart);

        OrderCartDTO orderCartDTO = new OrderCartDTO();
        orderCartDTO.setId(orderSaved.getId());
        orderCartDTO.setCreatedAt(orderSaved.getCreatedAt());
        orderCartDTO.setUser(orderSaved.getUser());
        orderCartDTO.setTotalPrice(orderSaved.getTotalPrice());

        return orderCartDTO;
    }
}
