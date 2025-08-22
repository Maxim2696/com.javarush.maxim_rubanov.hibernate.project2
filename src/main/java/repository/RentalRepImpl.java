package repository;

import datasource.MySessionFactory;
import entity.Inventory;
import entity.Rental;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.entity_rep.RentalRep;
import java.util.List;

public class RentalRepImpl implements RentalRep {
    @Override
    public int updateReturnDate(Long customerId, Long filmId) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Query<Rental> queryRental = session.createQuery("select r FROM Rental r where returnDate = null and r.customer.customerId = :customerId and r.inventory.film.filmId = :filmId", Rental.class);
            queryRental.setParameter("customerId", customerId);
            queryRental.setParameter("filmId", filmId);
            List<Rental> rental = queryRental.list();
            session.beginTransaction();
            for (Rental r : rental) {
                Query query = session.createQuery("UPDATE Rental set returnDate = now() where rentalId = :r");
                query.setParameter("r", r.getRentalId());
                query.executeUpdate();
                session.flush();
            }
            session.getTransaction().commit();
            return rental.size();
        }
    }

    @Override
    public List<Rental> getRentalsByReturnInventory(List<Inventory> inventory) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Query<Rental> query = session.createQuery("select r FROM Rental r where inventory in (:inventory) and returnDate is not null", Rental.class);
            query.setParameterList("inventory", inventory);
            return query.list();
        }
    }

    @Override
    public Rental addRental(Rental rental) {
        long rentalId;
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            rentalId = (Long) session.save(rental);
            session.getTransaction().commit();
        }
        return getRentalById(rentalId);
    }

    @Override
    public Rental getRentalById(Long rentalId) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Query<Rental> query = session.createQuery("select r FROM Rental r where rentalId = :rentalId", Rental.class);
            query.setParameter("rentalId", rentalId);
            return query.uniqueResult();
        }
    }
}
