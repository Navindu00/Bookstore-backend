package com.bookflix.bookstore.service;

import org.springframework.stereotype.Service;

import com.bookflix.bookstore.dto.UserProfileDTO;
import com.bookflix.bookstore.entity.User;

@Service
public interface UserService {
    User updateUser(Long id, UserProfileDTO userProfileDTO);
    User getUserById(Long id);
}
