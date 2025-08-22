package service;

import datasource.MySessionFactory;
import entity.*;
import org.hibernate.Session;
import repository.entity_rep.RentalRep;

import java.util.Date;
import java.util.List;

public class RentalService {
    private final RentalRep rentalRep;
    private Date timeOnRental;
    private static final Long STANDARD_TIME_ON_RENTAL = 1_000_000_000L;

    public RentalService(RentalRep rentalRep) {
        this.rentalRep = rentalRep;
    }

    public int returnRentalFilm(Long customerId, Long filmId) {
        return rentalRep.updateReturnDate(customerId, filmId);
    }

    public List<Rental> getRentalByInventory(List<Inventory> inventory) {
        return rentalRep.getRentalsByReturnInventory(inventory);
    }

    public Rental getRentalById(Long rentalId) {
        return rentalRep.getRentalById(rentalId);

    }

    public Rental createRental(Customer customer, Inventory inventory, Store store) {
        Rental rental = new Rental();
        Date newDate = getTimeOnRental();
        rental.setCustomer(customer);
        rental.setInventory(inventory);
        rental.setReturnDate(newDate);
        rental.setStaff(store.getStaff());
        return rentalRep.addRental(rental);
    }

    private Date getTimeOnRental() {
        return new Date(new Date().getTime() + 1_000_000_000L);
    }
}
