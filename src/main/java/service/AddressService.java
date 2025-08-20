package service;

import entity.Address;
import jakarta.persistence.NoResultException;
import repository.entity_rep.AddressRep;

public class AddressService {
    private final AddressRep addressRep;

    public AddressService(AddressRep addressRep) {
        this.addressRep = addressRep;
    }

    public Address getAddressOrCreate(String addressName, String districtName, String postalCode, String phoneNumber, String cityName, String countryName) {
        try {
            return addressRep.get(addressName, districtName, postalCode, phoneNumber, cityName);
        }
        catch (NoResultException e) {
            return addressRep.add(addressName, districtName, postalCode, phoneNumber, cityName, countryName);
        }
    }
}
