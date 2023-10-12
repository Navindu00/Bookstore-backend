package com.bookflix.bookstore.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookflix.bookstore.entity.User;
import com.bookflix.bookstore.payloads.request.AuthenticationRequest;
import com.bookflix.bookstore.payloads.request.RegisterRequest;
import com.bookflix.bookstore.payloads.response.AuthenticationResponse;
import com.bookflix.bookstore.payloads.response.MessageResponse;
import com.bookflix.bookstore.repository.UserRepository;
import com.bookflix.bookstore.security.jwt.JwtUtils;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public Object register(RegisterRequest registerRequest) {
        if(userRepository.existsByUsername(registerRequest.getUsername())){
            return new MessageResponse("Username is already taken!");
        }

        if(userRepository.existsByEmail(registerRequest.getEmail())){
            return new MessageResponse("Email is already in use!");
        }

        User user = User.builder()
                    .username(registerRequest.getUsername())
                    .email(registerRequest.getEmail())
                    .password(passwordEncoder.encode(registerRequest.getPassword()))
                    .role(registerRequest.getRole())  ///wenaskaranna ona
                    .build();

        userRepository.save(user);
        return new MessageResponse("User created Successfully!");
        // String token = jwtUtils.generateToken(user);

        // return AuthenticationResponse.builder()
        //         .jwt(token)
        //         .id(savedUser.getId())
        //         .username(savedUser.getUsername())  
        //         .email(savedUser.getEmail())              
        //         .build();
    }

    public Object login(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        User user = userRepository.findByUsername(authenticationRequest.getUsername()).orElseThrow();
        String token = jwtUtils.generateToken(user);

        return AuthenticationResponse.builder()
                .jwt(token)
                .id(user.getId())
                .username(user.getUsername())  
                .email(user.getEmail())              
                .build();
    }
    
}
