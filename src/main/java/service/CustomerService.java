package service;

import entity.Address;
import entity.Customer;
import jakarta.persistence.NoResultException;
import repository.entity_rep.CustomerRep;

public class CustomerService {
    private final CustomerRep customerRep;
    public CustomerService(CustomerRep customerRep) {
        this.customerRep = customerRep;
    }

    public Customer getCustomer(String firstName, String lastName) {
        try {
            return customerRep.get(firstName, lastName);
        }
        catch (NoResultException e) {
            return null;
        }
    }

    public Customer createCustomer(String firstName, String lastName, String email, Address address, Long storeId) {
        return customerRep.create(firstName, lastName, email, address,  storeId);
    }

    public Customer findCustomerById(Long id) {
        return customerRep.findById(id);
    }
}
