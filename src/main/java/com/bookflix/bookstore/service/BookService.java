package com.bookflix.bookstore.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.bookflix.bookstore.entity.Book;

@Service
public interface BookService {
    List<Book> getAllBooks();
    Book createBook(Book book);
    Book getBookById(Long id);
    Book updateBook(Long id, Book book);
    void deleteBook(Long id);
    List<Book> getBooksBySubCategoryId(Long subCategoryId);
    List<Book> getNewlyCreatedBooks();
}
