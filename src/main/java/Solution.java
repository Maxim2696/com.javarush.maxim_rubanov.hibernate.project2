import datasource.MySessionFactory;
import entity.*;
import org.hibernate.Session;
import repository.*;
import service.*;

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
//        returnRentalFilm(111L, 4L);
        rentalFilm(15L, 15L, 1L);
    }


    static Customer createCustomer(String firstName, String lastName, String email, Address address, Long storeId) {
        CustomerRepImpl  customerRep = new CustomerRepImpl();
        CustomerService customerService = new CustomerService(customerRep);
        return customerService.createCustomer(firstName, lastName, email, address, storeId);
    }

    static int returnRentalFilm(Long customerId, Long filmId) {
        RentalRepImpl rentalRep = new RentalRepImpl();
        RentalService  rentalService = new RentalService(rentalRep);
        return rentalService.returnRentalFilm(customerId, filmId);
    }

    static void rentalFilm(Long customerId, Long filmId, Long storeId) {
        StoreService storeService = new StoreService(new StoreRepImpl());
        CustomerService customerService = new CustomerService(new CustomerRepImpl());
        InventoryService inventoryService = new InventoryService(new InventoryRepImpl());
        RentalService rentalService = new RentalService(new RentalRepImpl());

        Store store = storeService.getStore(storeId);
        Customer customer = customerService.findCustomerById(customerId);
        List<Inventory> inventoryList = inventoryService.findInventoryByFilmId(filmId);
        List<Rental> rentalList = rentalService.getRentalByInventory(inventoryList);
        Rental rental;
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            if (!rentalList.isEmpty()) {
                Inventory inventory = rentalList.get(0).getInventory();
                rental = rentalService.createRental(customer, inventory, store);
            }
            else {
                FilmService filmService = new FilmService(new FilmRepImpl());
                Film film = filmService.getFilm(filmId);
                rental = rentalService.createRental(customer, inventoryService.createInventory(film, store), store);
            }
            PaymentService paymentService = new PaymentService(new PaymentRepImpl());
            paymentService.addPaymentByCustomerByRental(customer, store.getStaff(), rental, 2.99);
            session.getTransaction().commit();
        }
    }

    static Film createNewfilm() {
        return new Film();
    }

}
