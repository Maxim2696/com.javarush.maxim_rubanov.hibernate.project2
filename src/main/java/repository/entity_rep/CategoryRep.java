package repository.entity_rep;

import entity.Category;
import entity.Film;

public interface CategoryRep {
    void addCategory(Category category);
    Category getCategory(String name);
}
