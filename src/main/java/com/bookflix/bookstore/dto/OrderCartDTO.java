package com.bookflix.bookstore.dto;

import java.time.LocalDateTime;

import com.bookflix.bookstore.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderCartDTO {
    private Long id;
    private LocalDateTime createdAt;
    private Double totalPrice;
    private User user;
}
