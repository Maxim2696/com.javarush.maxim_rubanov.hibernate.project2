package service;

import entity.Category;
import repository.entity_rep.CategoryRep;

public class CategoryService {
    private final CategoryRep categoryRep;

    public CategoryService(CategoryRep categoryRep) {
        this.categoryRep = categoryRep;
    }

    public Category getCategoryByName(String name) {
        return categoryRep.getCategory(name);
    }

    public void addCategory(Category category) {
        categoryRep.addCategory(category);
    }
}
