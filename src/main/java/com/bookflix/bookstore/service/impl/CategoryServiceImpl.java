package com.bookflix.bookstore.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bookflix.bookstore.entity.Category;
import com.bookflix.bookstore.repository.CategoryRepository;
import com.bookflix.bookstore.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Category Not Found"));
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        Category existingCategory = categoryRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Category Not Found"));

        if(existingCategory != null){
            existingCategory.setName(category.getName());
            existingCategory.setDescription(category.getDescription());

            return categoryRepository.save(existingCategory);
        }else{
            return null;
        }

    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Category Not Found"));
        categoryRepository.delete(category);
    }
}
