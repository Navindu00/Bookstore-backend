package com.bookflix.bookstore.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bookflix.bookstore.entity.SubCategory;
import com.bookflix.bookstore.service.SubCategoryService;

@RestController
@CrossOrigin(origins = "*")
public class SubCategoryController {
    
    @Autowired
    private SubCategoryService subCategoryService;

    @GetMapping("/allsubcategories")
    public ResponseEntity<?> getAllSubCategories(){
        try {
            List<SubCategory> subCategories = subCategoryService.getAllSubCategories();
            return ResponseEntity.status(HttpStatus.OK).body(subCategories);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/subcategories/{id}")
    public ResponseEntity<?> getSubCategoryById(@PathVariable Long id){
        try {
            SubCategory subCategory = subCategoryService.getSubCategoryById(id);
            return ResponseEntity.status(HttpStatus.OK).body(subCategory);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/categories/{categoryId}/subcategories")
    public ResponseEntity<?> getSubcategoriesByCategoryId(@PathVariable Long categoryId){
        try {
            List<SubCategory> subCategories = subCategoryService.getSubCategoriesByCategoryId(categoryId);
            return ResponseEntity.status(HttpStatus.OK).body(subCategories);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
