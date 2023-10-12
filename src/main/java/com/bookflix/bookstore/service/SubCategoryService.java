package com.bookflix.bookstore.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.bookflix.bookstore.entity.SubCategory;

@Service
public interface SubCategoryService {
    List<SubCategory> getAllSubCategories();
    SubCategory createSubCategory(SubCategory subCategory);
    SubCategory getSubCategoryById(Long id);
    SubCategory updateSubCategory(Long id, SubCategory subCategory);
    void deleteSubCategory(Long id);
    List<SubCategory> getSubCategoriesByCategoryId(Long categoryId);
}
