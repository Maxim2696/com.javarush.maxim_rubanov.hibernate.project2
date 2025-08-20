package repository.entity_rep;

import entity.Address;
import entity.Customer;

public interface CustomerRep {
    Customer get(String firstName, String lastName);
    Customer create(String firstName, String lastName, String email, Address address, Long storeId);
    Customer findById(Long id);
}
