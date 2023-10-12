package com.bookflix.bookstore.payloads.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    
    private String jwt;
    private Long id;
    private String username;
    private String email;
}
