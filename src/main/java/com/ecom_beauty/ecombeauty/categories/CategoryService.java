
package com.ecom_beauty.ecombeauty.categories;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAllCategories();
    Optional<Category> getCategoryById(Integer id);
    Category saveCategory(Category category);
    void deleteCategory(Integer id);
    Optional<Category> getCategoryByName(String name);
}