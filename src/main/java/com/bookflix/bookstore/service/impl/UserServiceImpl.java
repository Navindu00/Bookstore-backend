package com.bookflix.bookstore.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bookflix.bookstore.dto.UserProfileDTO;
import com.bookflix.bookstore.entity.User;
import com.bookflix.bookstore.repository.UserRepository;
import com.bookflix.bookstore.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Value("${application.bookstore.upload.directory}") 
    private String uploadDirectory;

    @Override
    public User updateUser(Long id, UserProfileDTO userProfileDTO) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User Not Found"));
        if(existingUser != null){

            MultipartFile file = userProfileDTO.getProfileImage();
            String filename = file.getOriginalFilename();
            String filePath = uploadDirectory + File.separator + filename;

            try {
                file.transferTo(new File(filePath));
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            existingUser.setProfileImage(filename);
            return userRepository.save(existingUser);
        }
        
        return null;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User Not Found"));
    }
    
}
