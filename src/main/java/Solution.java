import datasource.MySessionFactory;
import entity.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.AddressRepImpl;
import repository.CityRepImpl;
import repository.CountryRepImpl;
import repository.CustomerRepImpl;
import service.AddressService;
import service.CityService;
import service.CountryService;
import service.CustomerService;

import java.util.List;

public class Solution {

    public static void main(String[] args) {
        CityRepImpl cityRep = new CityRepImpl();
        CountryRepImpl countryRep = new CountryRepImpl();
        CityService cityService = new CityService(cityRep);
        CountryService countryService = new CountryService(countryRep);
        AddressService addressService = new AddressService(new AddressRepImpl(countryService, cityService));
        CustomerRepImpl  customerRep = new CustomerRepImpl();
        CustomerService customerService = new CustomerService(customerRep);

//        Address address = addressService.getAddressOrCreate("16 Rouse Street", "Pitee", "941021", "9182736452", "Berlin", "Germany");
//        Customer customer = createCustomer("Johna", "Smitha", "Johna12.Smitha21@cool.com", address, 1L);
//        System.out.println(customerService.getCustomer("Johna", "Smitha"));

        returnRentalFilm(130L, 80L);

    }


    static Customer createCustomer(String firstName, String lastName, String email, Address address, Long storeId) {
        CustomerRepImpl  customerRep = new CustomerRepImpl();
        CustomerService customerService = new CustomerService(customerRep);
        return customerService.createCustomer(firstName, lastName, email, address, storeId);
    }

    static void returnRentalFilm(Long customerId, Long filmId) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
//            CustomerService customerService = new CustomerService(new CustomerRepImpl());
//            Customer customer = customerService.findCustomerById(customerId);

//            Query<Film> filmQuery = session.createQuery("select f from Film f where filmId = :filmId", Film.class);
//            filmQuery.setParameter("filmId", filmId);
//            Film film = filmQuery.getSingleResult();

//            Query<Inventory> queryInventory = session.createQuery("select i From Inventory i where film = :film", Inventory.class);
//            queryInventory.setParameter("film", film);
//            List<Inventory> inventories = queryInventory.list();

            Query<Rental> query = session.createQuery("select r FROM Rental r where r.customer.customerId = :customerId " +
                    "and r.inventory.film.filmId = :filmId", Rental.class);
            query.setParameter("customerId", customerId);
            query.setParameter("filmId", filmId);
            List<Rental> rental = query.list();
            session.beginTransaction();
            for (Rental r : rental) {
                session.remove(r);
                session.flush();
            }
            session.getTransaction().commit();
        }
    }

    static Film rentalNewFilm() {
        return new Film();
    }

    static Film createNewfilm() {
        return new Film();
    }

}
