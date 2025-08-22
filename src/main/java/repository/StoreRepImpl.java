package repository;

import datasource.MySessionFactory;
import entity.Store;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.entity_rep.StoreRep;

public class StoreRepImpl implements StoreRep {
    @Override
    public Store getById(Long id) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Query<Store> query = session.createQuery("select s from Store s where storeId =:id", Store.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        }
    }
}
