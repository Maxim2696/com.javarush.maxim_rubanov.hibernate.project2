package repository;

import datasource.MySessionFactory;
import entity.Inventory;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.entity_rep.InventoryRep;

import java.util.List;

public class InventoryRepImpl implements InventoryRep {
    @Override
    public List<Inventory> findListInventoryByFilmId(Long id) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()){
            Query<Inventory> query = session.createQuery("select i from Inventory i where i.film.filmId = :id", Inventory.class);
            query.setParameter("id", id);
            return query.getResultList();
        }
    }

    @Override
    public Inventory saveInventory(Inventory inventory) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()){
            session.beginTransaction();
            long id = (Long) session.save(inventory);
            session.getTransaction().commit();
            return get(id);
        }
    }

    @Override
    public Inventory get(Long id) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()){
            Query<Inventory> query = session.createQuery("select i from Inventory i where inventoryId = :id", Inventory.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        }
    }
}
