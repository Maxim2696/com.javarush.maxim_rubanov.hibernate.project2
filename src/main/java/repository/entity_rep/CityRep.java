package repository.entity_rep;

import entity.City;
import entity.Country;

public interface CityRep {
    City get(String nameCity);
    City create(String city, Country country);
}
