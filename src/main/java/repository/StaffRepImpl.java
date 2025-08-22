package repository;

import datasource.MySessionFactory;
import entity.Staff;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.entity_rep.StaffRep;

public class StaffRepImpl implements StaffRep {
    @Override
    public Staff getStaff(Long id) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Query<Staff> query = session.createQuery("select s from Staff s where staffId=:id", Staff.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        }
    }
}
