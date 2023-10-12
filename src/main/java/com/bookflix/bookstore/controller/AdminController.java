package com.bookflix.bookstore.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookflix.bookstore.entity.Book;
import com.bookflix.bookstore.entity.Category;
import com.bookflix.bookstore.entity.SubCategory;
import com.bookflix.bookstore.service.BookService;
import com.bookflix.bookstore.service.CategoryService;
import com.bookflix.bookstore.service.SubCategoryService;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    
    @Autowired
    private BookService bookService;

    @Autowired
    private SubCategoryService subCategoryService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/books")
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<?> createBook(@RequestBody Book book){
        try {
            Book bookCreated = bookService.createBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(bookCreated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/books/{id}")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody Book book){
        try {
            Book bookUpdated = bookService.createBook(book);
            return ResponseEntity.status(HttpStatus.OK).body(bookUpdated); 
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/books/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<?> deleteBook(@PathVariable Long id){
        try {
            bookService.deleteBook(id);
            return ResponseEntity.status(HttpStatus.OK).body("Book Deleted Successfully!");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @PostMapping("/categories")
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<?> createCategory(@RequestBody Category category){
        try {
            Category categoryCreated = categoryService.createCategory(category);
            return ResponseEntity.status(HttpStatus.CREATED).body(categoryCreated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/categories/{id}")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody Category category){
        try {
            Category categoryUpdated = categoryService.updateCategory(id, category);
            return ResponseEntity.status(HttpStatus.OK).body(categoryUpdated);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/categories/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.status(HttpStatus.OK).body("Category Deleted Successfully!");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @PostMapping("/subcategories")
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<?> createSubCategory(@RequestBody SubCategory subCategory){
        try {
            SubCategory subCategoryCreated = subCategoryService.createSubCategory(subCategory);
            return ResponseEntity.status(HttpStatus.CREATED).body(subCategoryCreated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/subcategories/{id}")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<?> updateSubCategory(@PathVariable Long id, @RequestBody SubCategory subCategory){
        try {
            SubCategory subCategoryUpdated = subCategoryService.updateSubCategory(id, subCategory);
            return ResponseEntity.status(HttpStatus.OK).body(subCategoryUpdated);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/subcategories/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<?> deleteSubcategory(@PathVariable Long id){
        try {
            subCategoryService.deleteSubCategory(id);
            return ResponseEntity.status(HttpStatus.OK).body("Sub Category Deleted Successfully!");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
