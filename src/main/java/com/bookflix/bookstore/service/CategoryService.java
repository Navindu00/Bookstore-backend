package com.bookflix.bookstore.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.bookflix.bookstore.entity.Category;

@Service
public interface CategoryService {
    List<Category> getAllCategories();
    Category createCategory(Category category);
    Category getCategoryById(Long id);
    Category updateCategory(Long id, Category category);
    void deleteCategory(Long id);
}
