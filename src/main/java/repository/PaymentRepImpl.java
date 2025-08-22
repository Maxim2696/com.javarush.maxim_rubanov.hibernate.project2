package repository;

import datasource.MySessionFactory;
import entity.Payment;
import org.hibernate.Session;
import repository.entity_rep.PaymentRep;

public class PaymentRepImpl implements PaymentRep {
    @Override
    public void create(Payment payment) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(payment);
            session.getTransaction().commit();
        }
    }
}
