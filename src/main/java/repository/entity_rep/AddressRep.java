package repository.entity_rep;

import entity.Address;

public interface AddressRep {
    Address get(String addressName, String districtName, String postalCode, String phoneNumber, String cityName);
    Address add(String addressName,  String districtName, String postalCode, String phoneNumber, String cityName, String countryName);
}
