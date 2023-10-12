package com.bookflix.bookstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookflix.bookstore.entity.Book;


@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
    @Query("SELECT b FROM Book b WHERE b.subCategory.id = :subCategoryId")
    Optional<List<Book>> getBooksBySubCategoryId(@Param("subCategoryId") Long subCategoryId);

    Optional<List<Book>> findTop2ByOrderByCreatedDesc();
}
