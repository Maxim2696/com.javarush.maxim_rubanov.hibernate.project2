package repository;

import datasource.MySessionFactory;
import entity.Category;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.entity_rep.CategoryRep;

public class CategoryRepImpl implements CategoryRep {
    @Override
    public void addCategory(Category category) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(category);
            session.getTransaction().commit();
        }
    }

    @Override
    public Category getCategory(String name) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Query<Category> query = session.createQuery("select c from Category c where name=:name", Category.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        }
    }
}
