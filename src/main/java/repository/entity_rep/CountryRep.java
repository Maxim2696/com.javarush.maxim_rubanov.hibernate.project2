package repository.entity_rep;

import entity.Country;

public interface CountryRep {
    Country get(String nameCountry);
    Country add(String country);
}
