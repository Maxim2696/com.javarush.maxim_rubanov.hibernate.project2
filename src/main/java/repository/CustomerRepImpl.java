package repository;

import datasource.MySessionFactory;
import entity.Address;
import entity.Customer;
import entity.Store;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.entity_rep.CustomerRep;

public class CustomerRepImpl implements CustomerRep {
    @Override
    public Customer get(String firstName, String lastName) throws NoResultException {
        try (Session session = MySessionFactory.getSessionFactory().openSession()){
            Query<Customer> query = session.createQuery("select c from Customer c where firstName = :firstName and lastName = :lastName", Customer.class);
            query.setParameter("firstName", firstName);
            query.setParameter("lastName", lastName);
            return query.getSingleResult();
        }
    }

    @Override
    public Customer create(String firstName, String lastName, String email, Address address, Long store_id) {
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setAddress(address);
        customer.setIsActive(true);
        try (Session session = MySessionFactory.getSessionFactory().openSession()){
            Store store = session.find(Store.class, store_id);
            customer.setStores(store);
            session.getTransaction().begin();
            session.save(customer);
            session.getTransaction().commit();
        }
        return get(firstName, lastName);
    }

    @Override
    public Customer findById(Long id) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()){
            Query<Customer> customerQuery = session.createQuery("select c from Customer c where customerId = :customerId", Customer.class);
            customerQuery.setParameter("customerId", id);
            return customerQuery.getSingleResult();
        }
    }
}
