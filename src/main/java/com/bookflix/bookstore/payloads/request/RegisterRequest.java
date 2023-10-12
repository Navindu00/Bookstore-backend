package com.bookflix.bookstore.payloads.request;

import com.bookflix.bookstore.utils.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private Role role; //wenaskaranna ona
}
