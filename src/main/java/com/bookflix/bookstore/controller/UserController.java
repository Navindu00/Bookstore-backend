package com.bookflix.bookstore.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookflix.bookstore.dto.UserProfileDTO;
import com.bookflix.bookstore.entity.User;
import com.bookflix.bookstore.service.UserService;

@CrossOrigin(origins = "*")
@RestController
public class UserController {
    
    @Autowired
    UserService userService;

    @PostMapping("/user/{id}/profile")
    public ResponseEntity<?> updateUser(@PathVariable Long id,@ModelAttribute UserProfileDTO userProfileDTO){
        try {
            User user = userService.updateUser(id, userProfileDTO);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
         try {
            User user = userService.getUserById(id);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
