package repository;

import datasource.MySessionFactory;
import entity.City;
import entity.Country;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.entity_rep.CityRep;

public class CityRepImpl implements CityRep {
    @Override
    public City get(String nameCity) throws NoResultException {
        try (Session session = MySessionFactory.getSessionFactory().openSession()){
            Query<City> query = session.createQuery("select c from City c where cityName = :nameCity", City.class);
            query.setParameter("nameCity", nameCity);
            return query.getSingleResult();
        }
    }

    @Override
    public City create(String city, Country country) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            City result = new City();
            result.setCityName(city);
            result.setCountry(country);
            session.save(result);
            session.getTransaction().commit();
            return get(city);
        }
    }
}
