package com.bookflix.bookstore.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookflix.bookstore.entity.SubCategory;
import com.bookflix.bookstore.repository.CategoryRepository;
import com.bookflix.bookstore.repository.SubCategoryRepository;
import com.bookflix.bookstore.service.SubCategoryService;

@Service
public class SubCategoryServiceImpl implements SubCategoryService{

    @Autowired
    private SubCategoryRepository subCategoryRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<SubCategory> getAllSubCategories() {
        return subCategoryRepository.findAll();
    }

    @Override
    public SubCategory createSubCategory(SubCategory subCategory) {
        return subCategoryRepository.save(subCategory);
    }

    @Override
    public SubCategory getSubCategoryById(Long id) {
        return subCategoryRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Sub Category Not Found"));
    }

    @Override
    public SubCategory updateSubCategory(Long id, SubCategory subCategory) {
        SubCategory existingSubCategory = subCategoryRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Sub Category Not Found"));

        if(existingSubCategory != null){
            existingSubCategory.setName(subCategory.getName());
            existingSubCategory.setDescription(subCategory.getDescription());
            existingSubCategory.setCategory(subCategory.getCategory());

            return subCategoryRepository.save(existingSubCategory);
        }else{
            return null;
        }
    }

    @Override
    public void deleteSubCategory(Long id) {
        SubCategory category = subCategoryRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Sub Category Not Found"));
        subCategoryRepository.delete(category);
    }

    @Override
    public List<SubCategory> getSubCategoriesByCategoryId(Long categoryId) {
        categoryRepository.findById(categoryId).orElseThrow(() -> new NoSuchElementException("Category Not Found"));
        return subCategoryRepository.getSubCategoriesByCategoryId(categoryId).orElseThrow(() -> new NoSuchElementException("SubCategories Not Found"));
    }
}
