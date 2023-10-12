package com.bookflix.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bookflix.bookstore.dto.OrderCartDTO;
import com.bookflix.bookstore.entity.OrderCart;
import com.bookflix.bookstore.exception.OutofStockException;
import com.bookflix.bookstore.service.OrderCartService;

@RestController
@CrossOrigin(origins = "*")
public class OrderCartController {
    
    @Autowired
    private OrderCartService orderService;

    @PostMapping("/orders")
    public ResponseEntity<?> createOrder(@RequestBody OrderCart order, @RequestHeader(value = "Authorization") String token){
        try {
            OrderCartDTO orderCartDTO = orderService.createOrder(order, token);
            return ResponseEntity.status(HttpStatus.CREATED).body(orderCartDTO);
        } catch (OutofStockException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
