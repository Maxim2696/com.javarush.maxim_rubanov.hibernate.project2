package repository.entity_rep;

import entity.Inventory;
import entity.Rental;

import java.util.List;

public interface RentalRep {
    int updateReturnDate(Long customerId, Long filmId);
    List<Rental> getRentalsByReturnInventory(List<Inventory> inventory);
    Rental addRental(Rental rental);
    Rental getRentalById(Long rentalId);
}
