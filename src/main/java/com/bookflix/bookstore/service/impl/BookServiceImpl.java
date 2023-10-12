package com.bookflix.bookstore.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookflix.bookstore.entity.Book;
import com.bookflix.bookstore.repository.BookRepository;
import com.bookflix.bookstore.service.BookService;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Book Not Found"));
    }

    @Override
    public Book updateBook(Long id, Book book) {
        Book existingBook = bookRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Book Not Found"));

        if(existingBook != null){
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setDescription(book.getDescription());
            existingBook.setPrice(book.getPrice());
            existingBook.setQuantity(book.getQuantity());
            existingBook.setSubCategory(book.getSubCategory());

            return bookRepository.save(existingBook);
        }else{
            return null;
        }
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Book Not Found"));
        bookRepository.delete(book);
    }

    @Override
    public List<Book> getBooksBySubCategoryId(Long subCategoryId) {
        return bookRepository.getBooksBySubCategoryId(subCategoryId).orElseThrow(() -> new NoSuchElementException("Category Not Found"));
    }

    @Override
    public List<Book> getNewlyCreatedBooks() {
        return bookRepository.findTop2ByOrderByCreatedDesc().orElseThrow(() -> new NoSuchElementException("No Books are Found"));
    }
}
