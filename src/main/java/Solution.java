import datasource.MySessionFactory;
import entity.*;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class Solution {
    private static final Integer CUSTOMER_ACTIVE = 1;
    private static final Integer CUSTOMER_NON_ACTIVE = 0;

    public static void main(String[] args) {
        Address address = getAddress("15 Rouse Street", "Pite", "94102", "918273645", "Berlin");
        System.out.println(address);
        Customer customer = createCustomer("John", "Smith", "John12.Smith21@cool.com", address);
        System.out.println(customer);
    }


    static Customer createCustomer(String firstName, String lastName, String email, Address address) {
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setAddress(address);
        customer.setActive(CUSTOMER_ACTIVE);
        try (Session session = MySessionFactory.getSessionFactory().openSession()){
            Store store = session.find(Store.class, 1L);
            customer.setStores(store);
            session.getTransaction().begin();
            long id = (long) session.save(customer);
            session.getTransaction().commit();
            customer.setCustomerId(id);
        }
        return customer;
    }

    public static Rental returnRentalFilm(Long customerId) {
        return new Rental();
    }

    static Film rentalNewFilm() {
        return new Film();
    }

    static Film createNewfilm() {
        return new Film();
    }

    static Country getCountryOrCreate(String country) {
        Country result;
        try (Session session = MySessionFactory.getSessionFactory().openSession()){
            Query<Country> query = session.createQuery("select c from Country c where countryName = :nameCountry", Country.class);
            query.setParameter("nameCountry", country);
            result = query.getSingleResult();
            return result;
        }
        catch (NoResultException e) {
            result = new Country();
            result.setCountryName(country);
            return result;
        }
    }

    static City getCity(String city) {
        City result;
        try (Session session = MySessionFactory.getSessionFactory().openSession()){
            Query<City> query = session.createQuery("select c from City c where cityName = :nameCity", City.class);
            query.setParameter("nameCity", city);
            result = query.getSingleResult();
            return result;
        }
        catch (NoResultException e) {
            return null;
        }
    }

    static City createCity(String city, Country country) {
        City result;
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            result = new City();
            result.setCityName(city);
            result.setCountry(country);
            long id = (Long) session.save(result);
            result.setCityId(id);
            session.getTransaction().commit();
            return result;
        }
    }

    private static Address getAddress(String addressName,  String districtName, String postalCode, String phoneNumber, String cityName) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Query<Address> query = session.createQuery("select a FROM Address a " +
                    "where addressName = :address and districtName = :district and postalCode = :postal " +
                    "and phoneNumber = :phone and city = :city", Address.class);
            query.setParameter("address", addressName);
            query.setParameter("district", districtName);
            query.setParameter("postal", postalCode);
            query.setParameter("phone", phoneNumber);
            query.setParameter("city", getCity(cityName));
            return query.getSingleResult();
        }
        catch (NoResultException e) {
            return createAddress(addressName, districtName, postalCode, phoneNumber, cityName);
        }
    }

    private static Address createAddress(String addressName,  String districtName, String postalCode, String phoneNumber, String cityName) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Address address = new Address();
            address.setAddressName(addressName);
            address.setDistrictName(districtName);
            address.setPostalCode(postalCode);
            address.setPhoneNumber(phoneNumber);
            City city = getCity(cityName);
            if (city == null) {
                city = createCity(cityName, getCountryOrCreate("Germany"));
            }
            address.setCity(city);
            session.getTransaction().begin();
            long id = (long) session.save(address);
            session.getTransaction().commit();
            address.setAddressId(id);
            return address;
        }
    }
}
