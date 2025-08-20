package repository;

import datasource.MySessionFactory;
import entity.Address;
import entity.City;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.entity_rep.AddressRep;
import service.CityService;
import service.CountryService;

public class AddressRepImpl implements AddressRep {
    private final CountryService serviceCountry;
    private final CityService cityService;

    public AddressRepImpl(CountryService serviceCountry, CityService cityService) {
        this.serviceCountry = serviceCountry;
        this.cityService = cityService;
    }

    @Override
    public Address get(String addressName, String districtName, String postalCode, String phoneNumber, String cityName) throws NoResultException{
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Query<Address> query = session.createQuery("select a FROM Address a " +
                    "where addressName = :address and districtName = :district and postalCode = :postal " +
                    "and phoneNumber = :phone and city = :city", Address.class);
            query.setParameter("address", addressName);
            query.setParameter("district", districtName);
            query.setParameter("postal", postalCode);
            query.setParameter("phone", phoneNumber);
            query.setParameter("city", cityService.getCity(cityName));
            return query.getSingleResult();
        }
    }


    @Override
    public Address add(String addressName,  String districtName, String postalCode, String phoneNumber, String cityName, String countryName) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Address address = new Address();
            address.setAddressName(addressName);
            address.setDistrictName(districtName);
            address.setPostalCode(postalCode);
            address.setPhoneNumber(phoneNumber);
            City city = cityService.getCity(cityName);
            if (city == null) {
                city = cityService.createCity(cityName, serviceCountry.getCountryOrCreate(countryName));
            }
            address.setCity(city);
            session.getTransaction().begin();
            session.save(address);
            session.getTransaction().commit();
            return get(addressName, districtName, postalCode, phoneNumber, cityName);
        }
    }
}
