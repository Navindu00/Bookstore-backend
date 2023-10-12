package com.bookflix.bookstore.dto;


import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class UserProfileDTO {
    private MultipartFile profileImage;
    //private Set<Long> faveuroteBooksId; ///////awul nm ayin karanna
}
