package service;

import entity.Country;
import jakarta.persistence.NoResultException;
import repository.CountryRepImpl;

public class CountryService {
    private final CountryRepImpl rep;
    public CountryService(CountryRepImpl rep) {
        this.rep = rep;
    }

    public Country getCountryOrCreate(String country) {
        try {
            return rep.get(country);
        }
        catch (NoResultException e) {
            return rep.add(country);
        }
    }
}
