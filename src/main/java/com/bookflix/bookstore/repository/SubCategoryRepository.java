package com.bookflix.bookstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookflix.bookstore.entity.SubCategory;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long>{
    @Query("SELECT s FROM SubCategory s WHERE s.category.id = :categoryId")
    Optional<List<SubCategory>> getSubCategoriesByCategoryId(@Param("categoryId") Long categoryId);
}
