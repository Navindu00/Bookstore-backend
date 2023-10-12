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

import com.bookflix.bookstore.entity.Book;
import com.bookflix.bookstore.service.BookService;

@RestController
@CrossOrigin(origins = "*")
public class BookController {
    
    @Autowired
    private BookService bookService;

    @GetMapping("/allbooks")
    public ResponseEntity<?> getAllBooks(){
        try {
            List<Book> books = bookService.getAllBooks();
            return ResponseEntity.status(HttpStatus.OK).body(books);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<?> getBookById(@PathVariable long id){
        try {
            Book book = bookService.getBookById(id);
            return ResponseEntity.status(HttpStatus.OK).body(book);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/subcategory/{subCategoryId}/books")
    public ResponseEntity<?> getBooksBySubCategoryId(@PathVariable Long subCategoryId){
        try {
            List<Book> books = bookService.getBooksBySubCategoryId(subCategoryId);
            return ResponseEntity.status(HttpStatus.OK).body(books);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/allbooks/newlycreated")
    public ResponseEntity<?> getNewlyCreatedBooks(){
        try {
            List<Book> books = bookService.getNewlyCreatedBooks();
            return ResponseEntity.status(HttpStatus.OK).body(books);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
