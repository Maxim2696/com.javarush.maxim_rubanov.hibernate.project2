package service;

import entity.City;
import entity.Country;
import jakarta.persistence.NoResultException;
import repository.CityRepImpl;

public class CityService {
    private final CityRepImpl cityRep;

    public CityService(CityRepImpl cityRep) {
        this.cityRep = cityRep;
    }

    public City getCity(String cityName) throws NoResultException {
        return cityRep.get(cityName);
    }

    public City createCity(String cityName, Country country) {
        return cityRep.create(cityName, country);
    }
}
